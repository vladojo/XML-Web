import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {NgxPaginationModule} from 'ngx-pagination';
import { NgbModule, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { HInterceptorService } from './h-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { LoginComponent } from './component/login/login.component';
import { AuthService } from './service/user/auth.service';
import { UserService } from './service/user/user.service';
import { AddHousingUnitComponent } from './component/add-housing-unit/add-housing-unit.component';
import { HousingUnitService } from './service/housing-unit.service';
import { MessagesComponent } from './component/messages/messages.component';
import { MessageService } from './service/message.service';
import { ReservationsComponent } from './component/reservations/reservations.component';
import { ViewHousingUnitsComponent } from './component/view-housing-units/view-housing-units.component';
import { MeniBarComponent } from './component/meni-bar/meni-bar.component';



const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'housing-unit-add', component: AddHousingUnitComponent },
  { path: 'housing-unit-view', component: ViewHousingUnitsComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'messages', component: MessagesComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }, 
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    AddHousingUnitComponent,
    MessagesComponent,
    ReservationsComponent,
    ViewHousingUnitsComponent,
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
    UserService,
    HousingUnitService,
    MessageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
