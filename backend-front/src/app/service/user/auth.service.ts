import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { environment } from 'environments/environment';
import { LoginRequest } from 'app/model/loginRequest';
import { User } from 'app/model/user';


@Injectable()
export class AuthService {
  baseUrl: string = environment.baseUrl + '/korisnik';

  constructor(private http: HttpClient,
    private router: Router) { }

  
    login(email:string, password:string): Observable<User>{
      let login: LoginRequest = new LoginRequest();
      login.email = email;
      login.password = password;
      const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
      return this.http.post<User>(this.baseUrl + '/login', login);
    }

    signup(user: User): Observable<{}>{
      return this.http
        .post<{}>(this.baseUrl + '/register', user);
    }

    signupAgent(user: User): Observable<{}>{
      return this.http
        .post<{}>(this.baseUrl + '/rest/agents', user);
    }

    logout(): void {
      localStorage.removeItem('user');
      this.router.navigate(['login']);
    }

}