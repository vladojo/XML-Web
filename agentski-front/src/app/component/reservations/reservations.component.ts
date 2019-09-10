import { Component, OnInit } from '@angular/core';
import { User } from 'app/model/user';
import { UserService } from 'app/service/user/user.service';
import { AuthService } from 'app/service/user/auth.service';
import { Router } from '@angular/router';
import { Reservation } from 'app/model/reservation';
import { HousingUnitService } from 'app/service/housing-unit.service';
import { Rating } from 'app/model/rating';
import { HousingUnit } from 'app/model/housingUnit';
import { UnitType } from 'app/model/unitType';
import { Address } from 'app/model/address';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  accommodationToShow: Reservation[] = [];
  currentRate = 4;
  loggedUser: User = new User();
  p:any;

  constructor(private housingUnitService: HousingUnitService, private userService: UserService, private router: Router,
               private authService: AuthService) { 
    let res : User = JSON.parse(localStorage.getItem('korisnik'));
    if(res != null){
      this.loggedUser = res;

      this.getAllReservations();
    }
    else{
      this.router.navigate(['login']);
    }
  }

  getAllReservations(){
    this.housingUnitService.getReservations(this.loggedUser.id).subscribe(
      s => {
        this.accommodationToShow = s;
        
      },
      err =>{
        this.popuniPodatke();
      }
    )
  }

  popuniPodatke(){
    let reservation: Reservation = new Reservation();
    reservation.id = 1;
    reservation.price = 15;
    reservation.end = "end";
    reservation.start = "start";
    reservation.rating = new Rating();
    reservation.rating.id = 2;
    reservation.rating.value = 3;
    reservation.realised = false;
    reservation.unit = new HousingUnit();
    reservation.unit.type = new UnitType();
    reservation.unit.type.id = 3;
    reservation.unit.type.name = "ime tipa";
    reservation.unit.description = "neki opis";
    reservation.unit.adress = new Address();
    reservation.unit.adress.city = "grad";
    reservation.unit.adress.country = "zemlja";


    this.accommodationToShow = [];
    this.accommodationToShow.push(reservation);
    reservation.id = 5;
    this.accommodationToShow.push(reservation);
  }

  ngOnInit() {

  }

  realised(accommodation: Reservation){
    if(confirm("Reservation realised?")){
      accommodation.realised = true;
      this.housingUnitService.updateResevation(accommodation.id).subscribe(
        s => {

          this.getAllReservations();
        }
      )
    }
  }
}