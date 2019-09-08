import { Component, OnInit } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { HousingUnit } from 'app/model/housingUnit';
import { User } from 'app/model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { DatePipe } from '@angular/common';
import { Address } from 'app/model/address';
import { UnitType } from 'app/model/unitType';
import { Reservation } from 'app/model/reservation';
import { HousingUnitsService } from 'app/service/housingUnits/housing-units.service';
import { Search } from 'app/model/search';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-view-housing-units',
  templateUrl: './view-housing-units.component.html',
  styleUrls: ['./view-housing-units.component.css']
})
export class ViewHousingUnitsComponent implements OnInit {

  p: number;
  beginDate: NgbDate;
  endDate: NgbDate;
  accommodationToShow : HousingUnit[] = [];
  search: Search = new Search();
  loggedUser: User = new User();
  maxLength: number = 30;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  dropdownSettingsAdditionalService = {}
  dropdownSettings = {};
  selectedCategory: String;
  selectedAccommodationType: String;
  selectedAdditionalServices: String[] = [];

  clickedSearch: boolean = false;

  patternHigh: any = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$";

  constructor(private route: ActivatedRoute, private accomodationService: HousingUnitsService, private router: Router,
    public datepipe: DatePipe, public authService: AuthService) { 
   let res : User = JSON.parse(localStorage.getItem('user'));
   if(res != null){
     this.loggedUser = res;
   }
   else {
     this.loggedUser.email = ""
   }
 }

 ngOnInit() {
   this.getAll();
 }

 popuniPodatke(){
  let accommodation: HousingUnit = new HousingUnit();
  accommodation.id = 1;
  accommodation.adress = new Address();
  accommodation.adress.city = "grad";
  accommodation.adress.country = "zemlja";
  accommodation.description = "opis ,asvm dskops fdcndskacsm afdvopksmasfdv ksmfv ";
  accommodation.price = 500;
  accommodation.rating = 5;
  accommodation.type = new UnitType();
  accommodation.type.name = "neki tip";

  this.accommodationToShow = [];
  this.accommodationToShow.push(accommodation);
  accommodation.id = 5;
  this.accommodationToShow.push(accommodation);
}


  getAll(){
    this.accomodationService.getAll().subscribe(
      s => {
        this.accommodationToShow = s;
      },
      err => {
        this.popuniPodatke();
      }
    )

  }

  searchFunction(){
    this.clickedSearch = true;
    this.search.pocetak = this.toModel(this.beginDate);
    this.search.kraj = this.toModel(this.endDate);

    this.accomodationService.search(this.search).subscribe(
      s => this.accommodationToShow = s
    )
  }

  fromModel(date: string): NgbDateStruct {

    const parsedDate = /(\d\d\d\d)-(\d\d)-(\d\d)/.exec(date);
    if (parsedDate) {
      return <NgbDateStruct>{ year: Number(parsedDate[1]), month: Number(parsedDate[2]), day: Number(parsedDate[3]) };
    } else {
      return null;
    }
  }

  toModel(date: NgbDateStruct): string {
    if (date) {
      let dateString = date.year + '-' + date.month + '-' + date.day;
      return this.datepipe.transform(dateString, 'yyyy-MM-dd')
    } else {
      return null;
    }
  }

  book(accommodation: HousingUnit){
    if(this.beginDate != null && this.endDate!= null){
      let reservation : Reservation = new Reservation();
      reservation.start = this.beginDate.year + "-" + this.beginDate.month + "-" + this.beginDate.day;
      reservation.end = this.endDate.year + "-" + this.endDate.month + "-" + this.endDate.day;
      reservation.unit = accommodation;
  
      let res : User = JSON.parse(localStorage.getItem('user'));
  
      if(res == null){
        this.router.navigate(['login']);
      }
      else{
        this.accomodationService.book(reservation, this.loggedUser.id).subscribe(
          succ => {
  
          }
        )
      }
    }
    else{
      alert('popunite pocetni i krajnji datum!')
    }

  }
    

  

}
