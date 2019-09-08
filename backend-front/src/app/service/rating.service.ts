import { Injectable } from '@angular/core';
import { environment } from 'environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Rating } from 'app/model/rating';

@Injectable()
export class RatingService {

  baseUrl: string = environment.baseUrl + '/ocena';

  constructor(private http: HttpClient) { }

  oceni(reservationId: number, userId: number, rating: Rating): Observable<Rating>{
    return this.http.post<Rating>(this.baseUrl + '/rest/reservations/' + reservationId + '?value=' + rating.value
    + "&user=" + userId, rating);
  }
}
