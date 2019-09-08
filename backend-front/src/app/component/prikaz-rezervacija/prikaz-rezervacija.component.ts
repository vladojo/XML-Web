import { Component, OnInit } from '@angular/core';
import { Reservation } from 'app/model/reservation';
import { User } from 'app/model/user';
import { HousingUnitsService } from 'app/service/housingUnits/housing-units.service';
import { UserService } from 'app/service/user/user.service';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { Rating } from 'app/model/rating';
import { HousingUnit } from 'app/model/housingUnit';
import { UnitType } from 'app/model/unitType';
import { Address } from 'app/model/address';
import { RatingService } from 'app/service/rating.service';

@Component({
  selector: 'app-prikaz-rezervacija',
  templateUrl: './prikaz-rezervacija.component.html',
  styleUrls: ['./prikaz-rezervacija.component.css']
})
export class PrikazRezervacijaComponent implements OnInit {

  accommodationToShow: Reservation[] = [];
  currentRate = 4;
  pomOcena: number = 1;
  loggedUser: User = new User();
  p:any;

  constructor(private housingUnitService: HousingUnitsService, private userService: UserService, private router: Router,
               private authService: AuthService, private ratingService: RatingService) { 
    let res : User = JSON.parse(localStorage.getItem('user'));
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

  cancel(accommodation: Reservation){
    if(confirm("Cancel reservation?")){
      accommodation.realised = true;
      this.housingUnitService.updateResevation(accommodation.id, this.loggedUser.id).subscribe(
        s => {
          this.getAllReservations();
        }
      )
    }
  }

  oceni(accommodation: Reservation){
    let rating: Rating = new Rating();
    rating.value = this.pomOcena;
    accommodation.rating = rating;
    this.ratingService.oceni(accommodation.id, this.loggedUser.id, rating).subscribe(
      s =>{

      }
    )
  }
}