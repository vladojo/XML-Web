import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { UnitType } from 'app/model/unitType';
import { Option } from 'app/model/option';
import { HousingUnit } from 'app/model/housingUnit';

@Injectable()
export class HousingUnitService {

  constructor(private http: HttpClient) { }

  findAllTipovi(): Observable<UnitType[]> {
    return this.http.get<UnitType[]>(`/rest/unit-types`);
  }

  findAllUsluge(): Observable<Option[]> {
    return this.http.get<Option[]>(`/rest/options`);
  }

  add(smestaj: HousingUnit, idAgent: number): Observable<HousingUnit[]> {
    return this.http.post<HousingUnit[]>(`/rest/units/agent/${idAgent}`, smestaj);
  }

  // search(accommodationSearch: AccommodationSearch): Observable<Accommodation[]> {
  //   return this.http.post<Accommodation[]>(`rest/smestaj/pretraga`, accommodationSearch);
  // }

  getAll(): Observable<HousingUnit[]> {
    return this.http.get<HousingUnit[]>("rest/units");
  }

  // book(accommodationReservation: AccommodationReservation): Observable<AccommodationReservation> {
  //   return this.http.post<AccommodationReservation>(`/rest/rezervacije`, accommodationReservation);
  // }

  // getUserReservations(userId: number): Observable<AccommodationReservation[]> {
  //   //TODO: mozda treba popraviti ///rest/agenti/id/smestaji
  //   return this.http.get<AccommodationReservation[]>(`rest/agenti/${userId}/smestaji`);
  // }

  // getReviewsForAccommodation(accommodationId: number): Observable<Review[]> {
  //   return this.http.get<Review[]>(`rating/rate/room/${accommodationId}`);
  // }

  // getImagesForRoom(roomId: number): Observable<Image[]> {
  //   return this.http.get<Image[]>(`accommodationservice/reservations/room/${roomId}`);
  // }

  // updateResevation(reservation: AccommodationReservation): Observable<AccommodationReservation> {
  //   return this.http.get<AccommodationReservation>(`/rest/rezervacije/${reservation.id}`);
  // }

}