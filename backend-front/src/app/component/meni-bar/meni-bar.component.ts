import { Component, OnInit } from '@angular/core';
import { User } from 'app/model/user';
import { AuthService } from 'app/service/user/auth.service';
import { UserService } from 'app/service/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meni-bar',
  templateUrl: './meni-bar.component.html',
  styleUrls: ['./meni-bar.component.css']
})
export class MeniBarComponent implements OnInit {

  public loggedIn: boolean = false;
  isAdmin = false;
  loggedUser: User = new User();

  constructor(private authService: AuthService, private userService: UserService, private router: Router){
    let res = localStorage.getItem('user');
    if(res != null){
      this.loggedIn = true;
      this.loggedUser = JSON.parse(localStorage.getItem('user'));

      if(this.loggedUser.type == "ADMIN"){
        this.isAdmin = true;
      } 


    }
  }

  ngOnInit() {
  }

  logOut(){
    this.authService.logout();
    this.loggedIn = false;
    this.router.navigate(['login']);
  }

  

}
