import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './_auth/login/login.component';
import { HeaderComponent } from './layouts/header/header.component';
import { ForbiddenComponent } from './layouts/forbidden/forbidden.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './_auth/auth.guard';
import { LoginService } from './_auth/login.service';
import {AuthMiddlewareInterceptor} from "./_auth/auth-middleware.interceptor";
import {NgOptimizedImage} from "@angular/common";
import { FooterComponent } from './layouts/footer/footer.component';
import { UsersDashboardComponent } from './admin/users-dashboard/users-dashboard.component';
import { NotFoundComponent } from './layouts/not-found/not-found.component';
import { RendezVousComponent } from './rendez-vous/rendez-vous.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    AdminComponent,
    HomeComponent,
    LoginComponent,
    HeaderComponent,
    ForbiddenComponent,
    FooterComponent,
    UsersDashboardComponent,
    NotFoundComponent,
    RendezVousComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    NgOptimizedImage,
    ReactiveFormsModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:AuthMiddlewareInterceptor,
      multi:true
    },
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
