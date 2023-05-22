import { Component } from '@angular/core';
import {routeNames} from "../../routes";

@Component({
  selector: 'app-forbidden',
  templateUrl: './forbidden.component.html',

})
export class ForbiddenComponent {
  protected readonly routeNames = routeNames;
}
