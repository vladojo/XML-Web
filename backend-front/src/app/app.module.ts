import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {NgxPaginationModule} from 'ngx-pagination';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HInterceptorService } from './h-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { DatePipe } from '@angular/common';
import { AuthService } from './service/user/auth.service';
import { LoginComponent } from './component/login/login.component';
import { RegistrationComponent } from './component/registration/registration.component';
import { UsersViewComponent } from './component/users-view/users-view.component';
import { ViewHousingUnitsComponent } from './component/view-housing-units/view-housing-units.component';
import { HousingUnitsService } from './service/housingUnits/housing-units.service';



import { UserService } from './service/user/user.service';
import { MessagesComponent } from './component/messages/messages.component';
import { MessageService } from './service/message.service';
import { ManageOptionsComponent } from './component/manage-options/manage-options.component';
import { UnitOfHouseService } from './service/unit-of-house.service';
import { PrikazRezervacijaComponent } from './component/prikaz-rezervacija/prikaz-rezervacija.component';
import { MeniBarComponent } from './component/meni-bar/meni-bar.component';
import { RatingService } from './service/rating.service';


const appRoutes: Routes = [

  { path: 'login', component: LoginComponent },  
  { path: 'registration', component: RegistrationComponent },
  { path: 'registration/:id', component: RegistrationComponent },
  { path: 'usersView', component: UsersViewComponent },
  { path: 'prikazRezervacija', component: PrikazRezervacijaComponent },
  { path: 'housingUnitsView', component: ViewHousingUnitsComponent },
  { path: 'messages', component: MessagesComponent },
  { path: 'manageOptions', component: ManageOptionsComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  
  //{ path: '**', component: ViewAccommodationComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    RegistrationComponent,
    UsersViewComponent,
    ViewHousingUnitsComponent,
    MessagesComponent,
    ManageOptionsComponent,
    PrikazRezervacijaComponent,
    MeniBarComponent,
  ],
  imports: [
    BrowserModule,
    AngularFontAwesomeModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    NgxPaginationModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true, useHash: true } // <-- debugging purposes only
    )
  ],
  providers: [ //registrujem servise obaveznoo!!!!!!
    NgbActiveModal,
    DatePipe,
    AuthService,
    HousingUnitsService,
    UserService,
    MessageService,
    UnitOfHouseService,
    RatingService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
