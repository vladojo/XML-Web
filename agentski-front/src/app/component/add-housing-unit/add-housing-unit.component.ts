import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { UserService } from 'app/service/user/user.service';
import { UnitType } from 'app/model/unitType';
import { Option } from 'app/model/option';
import { HousingUnit } from 'app/model/housingUnit';
import { User } from 'app/model/user';
import { HousingUnitService } from 'app/service/housing-unit.service';

@Component({
  selector: 'app-add-housing-unit',
  templateUrl: './add-housing-unit.component.html',
  styleUrls: ['./add-housing-unit.component.css']
})
export class AddHousingUnitComponent implements OnInit {

  dropdownSettingsAdditionalServices;
  dropdownSettingsAccommodationType;
  additionalServices: Option[] = [];
  accommodationTypes: UnitType[] = [];
  additionalServicesDropDown: string[] = [];
  accommodationTypeDropDown: string[] = [];
  selectAdditionalServices: string[] = [];
  selectedAccommodationType: string;

  accommodation: HousingUnit = new HousingUnit();
  loggedUser: User = new User();


  constructor(private accommodationService: HousingUnitService, private router: Router, private authService: AuthService, 
    private userService: UserService) { 
    let res: User = JSON.parse(localStorage.getItem('korisnik'));
    this.loggedUser = res;
}

  ngOnInit() {
    // if(localStorage.getItem('newAccommodation') != null){
    //   this.accommodation = JSON.parse(localStorage.getItem('newAccommodation'));
    // }
    this.dropdownSettingsAdditionalServices = {
      singleSelection: false,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this.dropdownSettingsAccommodationType = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    this.accommodationService.findAllUsluge().subscribe(
      s => {
        this.additionalServices = s;
        let pom = [];
        s.forEach(element => {
          pom.push(element.name);
        })
        this.additionalServicesDropDown = pom;
      }
    )
    this.accommodationService.findAllTipovi().subscribe(
      s => {
        this.accommodationTypes = s;
        let pom = [];
        s.forEach(element => {
          pom.push(element.name);
        })
        this.accommodationTypeDropDown = pom;
      }
    )
   }

  managePrices(){  //pozove da sacuva
    if(this.inputOk()){
      this.accommodationTypes.forEach(element => {
        if(element.name == this.selectedAccommodationType){
          this.accommodation.type = element;
        }
      })

      this.additionalServices.forEach(additionalService => {
        this.selectAdditionalServices.forEach(stringAdditionalService => {
          if(additionalService.name == stringAdditionalService){
            this.accommodation.options.push(additionalService);
          }
        })
      })

      if(this.accommodation.daysForCancelling == 0){
        this.accommodation.allowedCancelling = false
      }
      else{
        this.accommodation.allowedCancelling = true;
      }

      //TODO: pozovi metodu da sacuva to
      //localStorage.setItem('newAccommodation', JSON.stringify(this.accommodation));

      this.accommodationService.add(this.accommodation, this.loggedUser.id).subscribe(
        succ => {
          this.router.navigate(['housing-units']);
        }
      )

      
    }
  }


  inputOk(){
    if(this.accommodation.address.country == ""){
      alert("You must enter country for housing unit");
      return false;
    }
    if(this.accommodation.address.city == ""){
      alert("You must enter city for  housing unit");
      return false;
    }
    if(this.accommodation.description == ""){
      alert("You must enter description for housing unit");
      return false;
    }

    return true;
  }

}
