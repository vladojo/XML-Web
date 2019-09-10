import { Component, OnInit } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { HousingUnit } from 'app/model/housingUnit';
import { User } from 'app/model/user';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { AuthService } from 'app/service/user/auth.service';
import { UserService } from 'app/service/user/user.service';
import { HousingUnitService } from 'app/service/housing-unit.service';
import { Reservation } from 'app/model/reservation';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Address } from 'app/model/address';
import { Rating } from 'app/model/rating';
import { UnitType } from 'app/model/unitType';

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

  patternHigh: any = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$";

  constructor(private route: ActivatedRoute, private accomodationService: HousingUnitService, private router: Router,
    public datepipe: DatePipe, public authService: AuthService, public userService: UserService) { 
   let res : User = JSON.parse(localStorage.getItem('korisnik'));
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
    this.accomodationService.getAll(this.loggedUser.id).subscribe(
      s => {
        this.accommodationToShow = s;
      },
      err => {
        this.popuniPodatke();
      }
    )

  }

  

  book(accommodation: HousingUnit){

    if(this.beginDate != null && this.endDate != null){
    let reservation : Reservation = new Reservation();
    reservation.start = this.beginDate.year + "-" + this.beginDate.month + "-" + this.beginDate.day;
    reservation.end = this.endDate.year + "-" + this.endDate.month + "-" + this.endDate.day;
    reservation.unit = accommodation;

    let res : User = JSON.parse(localStorage.getItem('korisnik'));

    if(res == null){
      this.router.navigate(['login']);
    }
    else{
      this.accomodationService.book(reservation).subscribe(
        succ => {

        }
      )
    }
  }
  else{
    alert('popunite pocetni i krajnji datum');
  }
}

}
