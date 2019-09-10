import { Component, OnInit } from '@angular/core';
import { LoginRequest } from 'app/model/loginRequest';
import { User } from 'app/model/user';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { UserService } from 'app/service/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  korisnik: LoginRequest;
  public logovaniKorisnik: User;


  constructor(private auth: AuthService, private router: Router, private korisnikService: UserService) { 
    this.korisnik = new LoginRequest();
  }

  ngOnInit() {
  }

  login(){
    let message: string = this.proveriUnosKorisnik();
    if(message === "OK")
    {
      localStorage.removeItem('korisnik');
      this.auth.login(this.korisnik.email, this.korisnik.password)
          .subscribe(
            res => 
            {
              console.log(res);
              localStorage.setItem('korisnik', JSON.stringify(res));
              this.logovaniKorisnik = res;
              this.router.navigate(['housing-unit-view']);
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