import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { LoginRequest } from 'app/model/loginRequest';
import { User } from 'app/model/user';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient,
    private router: Router) { }
  
    login(mejl:string, lozinka:string): Observable<User>{
      let login: LoginRequest = new LoginRequest();
      login.email = mejl;
      login.password = lozinka;
      const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
      return this.http
      .post<User>('/login', login);
    }

    logout(): void {
      localStorage.removeItem('korisnik');
      localStorage.removeItem('newAccommodation');
      this.router.navigate(['login']);
    }

}