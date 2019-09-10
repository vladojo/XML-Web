import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/user';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

}