import { Component, OnInit } from '@angular/core';
import { UnitType } from 'app/model/unitType';
import { Option } from 'app/model/option';
import { UnitOfHouseService } from 'app/service/unit-of-house.service';

@Component({
  selector: 'app-manage-options',
  templateUrl: './manage-options.component.html',
  styleUrls: ['./manage-options.component.css']
})
export class ManageOptionsComponent implements OnInit {

  unitTypes: UnitType[] = []
  options: Option[] = []
  newUnitType: UnitType = new UnitType();
  newOption: Option = new Option();

  constructor(private housingUnitService: UnitOfHouseService) 
  {

  }

  ngOnInit() {
    this.housingUnitService.getAllUnitTypes().subscribe(
      s =>{
        this.unitTypes = s;
      },
      err =>{
        this.popuniPodatke();
      }
    );

    this.housingUnitService.getAllOptions().subscribe(
      s =>{
        this.options = s;
      },
      err =>{
        this.popuniPodatke();
      }
    )
  }

  popuniPodatke(){
    let unit: UnitType = new UnitType();
    unit.id = 1;
    unit.name = "prvi";
    let unit2: UnitType = new UnitType();
    unit2.id = 2;
    unit2.name = "drugi";
    let unit3: UnitType = new UnitType();
    unit3.id = 3;
    unit3.name = "treci";

    this.unitTypes = [];
    this.unitTypes.push(unit);
    this.unitTypes.push(unit2);
    this.unitTypes.push(unit3);

    let option: Option = new Option();
    option.id = 1;
    option.name = "opcija1";
    option.description = "opsisis";
    let option2: Option = new Option();
    option2.id = 2;
    option2.name = "opcija222";
    option2.description = "opsisryeyts";
    let option3: Option = new Option();
    option3.id = 3;
    option3.name = "opcija3";
    option3.description = "oafsgdf";

    this.options = [];
    this.options.push(option);
    this.options.push(option2);
    this.options.push(option3);
  }

  addUnitType(){
    if(this.newUnitType.id == 0){
      this.housingUnitService.createUnitType(this.newUnitType).subscribe(
        s => {
          this.unitTypes.push(s);
        }
      )
    }
    else{
      this.housingUnitService.updateUnitType(this.newUnitType).subscribe(
        s => { 
          this.unitTypes.forEach(element =>{
            if(element.id == s.id){
              element.name = s.name;
            }
          }
        )}
      )
    }
    this.newUnitType = new UnitType();
  }

  deleteUnitType(accommodationType: UnitType){
    this.housingUnitService.deleteUnitType(accommodationType.id).subscribe(
      s => {
          let pomAccommodationType: UnitType = new UnitType();
          this.unitTypes.forEach(element => {
            if(element.id === accommodationType.id){
              pomAccommodationType = element;
            }
          })

          this.unitTypes.splice(this.unitTypes.indexOf(pomAccommodationType), 1)
      }
    )
  }

  editUnitType(accommodationType: UnitType){
    this.newUnitType.id = accommodationType.id;
    this.newUnitType.name = accommodationType.name;
  }

  addOption(){
    if(this.newOption.id == 0){
      this.housingUnitService.createOptions(this.newOption).subscribe(
        s => {
          this.options.push(s);          
        }
      )
    }
    else{
      this.housingUnitService.updateOption(this.newOption).subscribe(
        s => {
          this.options.forEach(element =>{
            if(element.id == s.id){
              element.name = s.name;
              element.description = s.description;
            }
          }
        )        
        }
      )
    }
    this.newOption = new Option();
  }

  deleteOption(additionalService: Option){
    this.housingUnitService.deleteOption(additionalService.id).subscribe(
      s => {
        let pomAdditionalService: Option = new Option();
        this.options.forEach(element => {
          if(element.id === additionalService.id){
            pomAdditionalService = element;
          }
        })
    
        this.options.splice(this.options.indexOf(pomAdditionalService), 1)
      }
    )
  }

  editOption(additionalService: Option){
    this.newOption.id = additionalService.id;
    this.newOption.name = additionalService.name;
    this.newOption.description = additionalService.description;
  }

}