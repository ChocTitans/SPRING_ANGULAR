import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {routeNames} from "./routes";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {
  constructor(
    private router: Router,
    ) {}
  public isDashboard() : boolean {
    return this.router.url.includes(routeNames.dashboard);
  }

  protected readonly routeNames = routeNames;
}
