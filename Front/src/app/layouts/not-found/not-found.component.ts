import { Component } from '@angular/core';
import {routeNames} from "../../routes";

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html'
})
export class NotFoundComponent {

  protected readonly routeNames = routeNames;
}
