import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { UnitType } from 'app/model/unitType';
import { Option } from 'app/model/option';
import { HousingUnit } from 'app/model/housingUnit';
import { Reservation } from 'app/model/reservation';

@Injectable()
export class HousingUnitService {

  constructor(private http: HttpClient) { }

  add(smestaj: HousingUnit, idAgent: number): Observable<HousingUnit[]> {
    return this.http.post<HousingUnit[]>(`/rest/units?agent=${idAgent}`, smestaj);
  }

  findAllTipovi(): Observable<UnitType[]> {
    return this.http.get<UnitType[]>(`/rest/unit-type`);
  }

  findAllUsluge(): Observable<Option[]> {
    return this.http.get<Option[]>(`/rest/bonus-options`);
  }

  getAll(agentId: number): Observable<HousingUnit[]> {
    return this.http.get<HousingUnit[]>("rest/units?agent=" + agentId);
  }

  book(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(`/rest/reservations`, reservation);
  }

  getReservations(id: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`rest/units/${id}/reservations`);
  }

  updateResevation(idReservation: number): Observable<Reservation> {
    return this.http.put<Reservation>(`/rest/reservations/${idReservation}`, {});
  }

}