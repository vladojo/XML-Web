import { Component, OnInit } from '@angular/core';
import { LoginRequest } from 'app/model/loginRequest';
import { User } from 'app/model/user';
import { AuthService } from 'app/service/user/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  korisnik: LoginRequest;
  public logovaniKorisnik: User;


  constructor(private auth: AuthService, private router: Router) { 
    this.korisnik = new LoginRequest();
  }

  ngOnInit() {
  }

  login(){
    let message: string = this.proveriUnosKorisnik();
    if(message === "OK")
    {
      localStorage.removeItem('user');
      this.auth.login(this.korisnik.email, this.korisnik.password)
          .subscribe(
            res => 
            {
              console.log(res);
              localStorage.setItem('user', JSON.stringify(res));
              this.logovaniKorisnik = res;

              if(this.logovaniKorisnik.type == 'ADMIN'){
                this.router.navigate(['usersView']);
              }
              else{
                this.router.navigate(['housingUnitsView']);
              }
              
            }
          )
        }
        else{
          alert(message);
        }
  }

  validateEmail(email: string) 
  {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  proveriUnosKorisnik(): string{
    if(this.korisnik.email === "" || this.korisnik.email.length > 60){
      return "Morate popuniti validnu e-mail adresu koja mora biti kraca od " + 60 + " karaktera";
    }
  
    if(!this.validateEmail(this.korisnik.email)){
      return "Morate uneti validnu e-mail adresu";
    }
    
    if(this.korisnik.password === "" || this.korisnik.password.length > 30){
      return "Morate popuniti validnu e-mail adresu koja mora biti kraca od " + 30 + " karaktera";
    }

    return "OK";
  }

}