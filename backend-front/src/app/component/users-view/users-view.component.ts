import { Component, OnInit } from '@angular/core';
import { User } from 'app/model/user';
import { AuthService } from 'app/service/user/auth.service';
import { Router } from '@angular/router';
import { UserService } from 'app/service/user/user.service';

@Component({
  selector: 'app-users-view',
  templateUrl: './users-view.component.html',
  styleUrls: ['./users-view.component.css']
})
export class UsersViewComponent implements OnInit {

  users: User[];
  dropdownSettings = {};
  p: any;
  loggedUser: User = new User();

  constructor(private authService: AuthService, private userService : UserService, 
    private router: Router) {
    let res = JSON.parse(localStorage.getItem('user'));
    if(res == null){
      //this.router.navigate(['login']);
    }
    if(res != null){
      this.loggedUser = res;
    }
  }

  ngOnInit() {
    this.userService.findAll().subscribe(
      s => {
        this.users = s
      },
      err => {
        this.popuniPodatke();
      }
    );

    this.dropdownSettings = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

  }

  popuniPodatke(){
      let user1: User = new User();
      user1.email = "s.bokic@yahoo.com";
      user1.firstName = "imenko";
      user1.lastName = "prezimenic";

      this.users = [];
      this.users.push(user1);
      this.users.push(user1);
      this.users.push(user1);
  }

  changeStateOfUser(user:User, boolState: string){
    user.state = boolState;
    this.userService.changeStateOfUser(user.id, boolState).subscribe(
      s => {
        this.userService.findAll().subscribe(
          novi => {
            this.users = novi
          }
        );
      }
    );
  }
}
