import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, throwError} from "rxjs";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";
import {Injectable} from "@angular/core";
import {routeNames} from "../routes";

@Injectable()
export class AuthMiddlewareInterceptor implements HttpInterceptor {

  constructor(
    private UserAuthService: AuthService,
    private router: Router
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) : any {

    if (req.headers.get('No-Auth') == "True") {
      return next.handle(req.clone());
    }

    const token = this.UserAuthService.getJwtToken();

    if (token != null) {
      req = this.addToken(req, token);
      return next.handle(req).pipe(
        catchError((error) => {
          if (error.status === 401) {
            this.UserAuthService.clear();
            this.router.navigate(['/' + routeNames.login]).then(() => console.log("Middleware intercepted request : Invalid token"));
          } else if (error.status === 403) {
            this.router.navigate(['/' + routeNames.forbidden]).then(() => console.log("Middleware intercepted request : Authorization error"));
          }
          return throwError(error);
        })
      );
    } else {
      this.router.navigate(['/' + routeNames.login]).then(() => console.log("Middleware intercepted request : Please Log in"));
    }
  }

  private addToken (req: HttpRequest<any>, token: string) : HttpRequest<any> {
    if (token == null) return req;
    return req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
}
