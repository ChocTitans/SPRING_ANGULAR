import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { LoginService } from "../login.service";
import { AuthService } from "../auth.service";
import { Router } from "@angular/router";
import {routeNames} from "../../routes";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',

})
export class LoginComponent implements OnInit {
  constructor(
    private userService: LoginService,
    private userAuthService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  authenticate(loginForm: NgForm) {
    this.userService.login(loginForm.value.email, loginForm.value.password).subscribe(
      (response: any) => {
        this.userAuthService.setJwtToken(response.access_token);
        this.userAuthService.setRefreshToken(response.refresh_token);
        this.userAuthService.setRole(response.role_name);

        const role = this.userAuthService.getRole();
        if (role === 'ROLE_ADMIN') {
          this.router.navigate(['/' + routeNames.dashboard]).then(() => console.log("[LoginComponent] navigate to /dashboard"));
        }
        else if (role === 'ROLE_UTILISATEUR') {
          this.router.navigate(['/' + routeNames.home]).then(() => console.log("[LoginComponent] navigate to /home"));
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
