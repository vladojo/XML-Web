import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/user';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  // findOne(id: number): Observable<User> {
  //   return this.http.get<User>(`/api/user/${id}`);
  // }

  // findAll(): Observable<User[]> {
  //   return this.http.get<User[]>(`/api/users`);
  // }

  // findByEmail(email: string): Observable<User> {
  //   return this.http.get<User>(`api/user/email/${email}`);
  // }
}