import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {LoginService} from "./login.service";
import {AuthService} from "./auth.service";
import {routeNames} from "../routes";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard  {

  constructor(
    private userAuthService : AuthService,
    private router: Router,
    private userService : LoginService
  ) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree
  {
    if (route.url[0].path === "login") {
      if (this.userAuthService.getJwtToken() != null) {
        this.router.navigate(['/'+ routeNames.home]).then(() => console.log("[AuthGuard] Authorization Error : Redirected to home page"));
        return false;
      }
      return true;
    }

    if (this.userAuthService.getJwtToken() != null) {
      const roles = route.data["roles"] as string[];
      if (roles) {
        if (this.userService.hasAuthorization(roles)) {
          return true;
        } else {
          this.router.navigate(['/' + routeNames.forbidden]).then(() => console.log("[AuthGuard] Authorization Error : Redirected to forbidden page"));
          return false;
        }
      }
    }
    this.router.navigate(['/'+ routeNames.login]).then(() => console.log("[AuthGuard] Authorization Error : Redirected to login page"));
    return false;
  }
}
