import { Injectable } from '@angular/core';
import { environment } from 'environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { UnitType } from 'app/model/unitType';
import { Observable } from 'rxjs/Observable';
import { Option } from 'app/model/option';
import { HousingUnit } from 'app/model/housingUnit';

@Injectable()
export class UnitOfHouseService {

  baseUrl: string = environment.baseUrl + '/smestaj';

  constructor(private http: HttpClient) { }

 

  add(smestaj: HousingUnit, idAgent: number): Observable<HousingUnit[]> {
    return this.http.post<HousingUnit[]>(this.baseUrl + `/rest/units/agent/${idAgent}`, smestaj);
  }

  //vrati sve smestaje
  getAll(): Observable<HousingUnit[]> {
    return this.http.get<HousingUnit[]>(this.baseUrl + "smestaj");
  }


  findAllTipovi(): Observable<UnitType[]> {
    return this.http.get<UnitType[]>(this.baseUrl + `/rest/unit-type`);
  }

  findAllUsluge(): Observable<Option[]> {
    return this.http.get<Option[]>(this.baseUrl + `/rest/bonus-options`);
  }

  getAllUnitTypes(): Observable<UnitType[]> {
    return this.http.get<UnitType[]>(this.baseUrl + "/rest/unit-type");
  }

  createUnitType(unitType: UnitType): Observable<UnitType> {
    return this.http.post<UnitType>(this.baseUrl + "/rest/unit-type", unitType);
  }

  deleteUnitType(idUnitType: number): Observable<{}> {
    return this.http.delete(this.baseUrl + `/rest/unit-type/${idUnitType}`);
  }

  updateUnitType(unitType: UnitType): Observable<UnitType> {
    return this.http.put<UnitType>(this.baseUrl + `/rest/unit-type/${unitType.id}`, unitType);
  }

  getAllOptions(): Observable<Option[]> {
    return this.http.get<Option[]>(this.baseUrl + "/rest/bonus-options");
  }

  createOptions(option: Option): Observable<Option> {
    return this.http.post<Option>(this.baseUrl + "/rest/bonus-options", option);
  }

  deleteOption(idOption: number): Observable<{}> {
    return this.http.delete(this.baseUrl + `/rest/bonus-options/${idOption}`);
  }

  updateOption(option: Option): Observable<Option> {
    return this.http.put<Option>(this.baseUrl + `/rest/bonus-options/${option.id}`, option);
  }
}