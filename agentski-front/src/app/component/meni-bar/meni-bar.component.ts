import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meni-bar',
  templateUrl: './meni-bar.component.html',
  styleUrls: ['./meni-bar.component.css']
})
export class MeniBarComponent implements OnInit {

  public loggedIn: boolean = false;

  constructor(private router: Router) { 
    let res = localStorage.getItem('korisnik');
    if(res != null){
      this.loggedIn = true;
    }
  }

  ngOnInit() {
  }

  logOut(){
    localStorage.removeItem('korisnik');
    this.loggedIn = false;
    this.router.navigate(['login']);
  }

}
