import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  public loggedIn: boolean = false;

  constructor(/*private authService: AuthService,*/ private router: Router){
    let res = localStorage.getItem('token');
    if(res != null){
      this.loggedIn = true;
    }
  }

  logOut(){
    //this.authService.logout();
    this.loggedIn = false;
    this.router.navigate(['login']);
  }
  
}
