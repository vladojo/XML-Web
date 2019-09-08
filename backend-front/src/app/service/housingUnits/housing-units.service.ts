import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { HousingUnit } from 'app/model/housingUnit';
import { Reservation } from 'app/model/reservation';
import { Search } from 'app/model/search';
import { environment } from 'environments/environment';

@Injectable()
export class HousingUnitsService {

  baseUrl: string = environment.baseUrl + '/smestaj';

  constructor(private http: HttpClient) { }


  getAll(): Observable<HousingUnit[]> {
    return this.http.get<HousingUnit[]>(this.baseUrl + "/rest/units");
  }


  search(accommodationSearch: Search): Observable<HousingUnit[]> {
    let params = new HttpParams();
    params = params.append('country', accommodationSearch.zemlja);
    params = params.append('city', accommodationSearch.grad);
    params = params.append('people', accommodationSearch.brojLjudi.toString());
    params = params.append('start', accommodationSearch.pocetak);
    params = params.append('end', accommodationSearch.kraj);
    return this.http.get<HousingUnit[]>(this.baseUrl + '/rest/units/search',{params: params})
  }


  book(reservation: Reservation, idUser: number): Observable<Reservation> {
    return this.http.post<Reservation>(this.baseUrl + `/rest/${idUser}/reservations`, reservation);
  }

  getReservations(id: number): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(this.baseUrl + `/rest/${id}/reservations`);
  }
  
  updateResevation(idReservation: number, idUser: number): Observable<Reservation> {
    return this.http.delete<Reservation>(this.baseUrl + `/rest/${idUser}/reservations/${idReservation}`);
  }

 
}