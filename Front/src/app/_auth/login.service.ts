import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {routeNames} from "../routes";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: "root"
})
export class LoginService {

  BACKEND_URL = `${environment.apiURL}/api/auth/`;
  requestHeader = new HttpHeaders(
    {
      "No-Auth": "True"
    }
  );

  constructor(
    private httpClient: HttpClient,
    private userAuthService: AuthService
  ) {
  }

  public login(email: String, password: String) {
    return this.httpClient.post(
      this.BACKEND_URL + routeNames.login,
      {email, password},
      {headers: this.requestHeader});
  }

  public hasAuthorization(allowedRoles: string[]): boolean {
    const userRole = this.userAuthService.getRole();
    if (userRole === null) {
      return false;
    }
    return allowedRoles.includes(userRole);
  }

}
