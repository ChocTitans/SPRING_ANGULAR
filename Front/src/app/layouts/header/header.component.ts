import { Component, OnInit } from "@angular/core";
import { AuthService } from "../../_auth/auth.service";
import { Router } from "@angular/router";
import {routeNames} from "../../routes";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

  BACKEND_URL = environment.apiURL;
  constructor(
    private userAuthService: AuthService,
    private router: Router,
    private httpClient: HttpClient
    ) {}

  ngOnInit(): void {}

  public isDashboard() : boolean {
    return this.router.url.includes(routeNames.dashboard);
  }

  public isAuthenticated() : boolean {
    return this.userAuthService.isAuthenticated();
  }
  logout() {
    //get request to /api/auth/logout with jwt token as bearer
    this.httpClient.get(`${this.BACKEND_URL}/api/auth/logout`, {
      headers: {
        'Authorization': 'Bearer ' + this.userAuthService.getJwtToken()
      }
    }).subscribe(
      () => {
        this.router.navigate([routeNames.index]).then(
          () => {
            this.userAuthService.clear();
          }
        );
      }
    )
  }
    protected readonly routeNames = routeNames;
}
