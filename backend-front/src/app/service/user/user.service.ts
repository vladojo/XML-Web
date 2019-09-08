import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { User } from 'app/model/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  baseUrl: string = environment.baseUrl + '/korisnik';

  findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl + '/rest/users');
  }

  findById(id: number): Observable<User> {
    return this.http.get<User>(this.baseUrl + '/rest/all-users/' + id);
  }

  findByEmail(email: string): Observable<User> {
    return this.http.get<User>(this.baseUrl + `/email/${email}`);
  }

  changeStateOfUser(id: number, korisnikState: string): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/rest/users/${id}?state=${korisnikState}`, {});
  }

}