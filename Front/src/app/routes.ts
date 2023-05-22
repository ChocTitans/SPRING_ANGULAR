import {Routes} from "@angular/router";
import {IndexComponent} from "./index/index.component";
import {AuthGuard} from "./_auth/auth.guard";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./_auth/login/login.component";
import {ForbiddenComponent} from "./layouts/forbidden/forbidden.component";
import {UsersDashboardComponent} from "./admin/users-dashboard/users-dashboard.component";
import {AdminComponent} from "./admin/admin.component";
import {NotFoundComponent} from "./layouts/not-found/not-found.component";
import { RendezVousComponent } from './rendez-vous/rendez-vous.component';
export const routeNames = {
  index: '',
  dashboard: 'dashboard',
  home: 'home',
  login: 'login',
  forbidden: 'forbidden',
  notFound: 'notfound',
  usersDashboard: 'dashboard/users',
  rendezVous: 'rendez-vous',
}

export const routes: Routes = [
  {path: routeNames.index, component: IndexComponent},
  {path: routeNames.home, component: HomeComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_ADMIN", "ROLE_UTILISATEUR"]}},
  {path: routeNames.login, component: LoginComponent, canActivate: [AuthGuard]},
  {path: routeNames.dashboard, component: AdminComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_ADMIN"]}},
  {path: routeNames.usersDashboard, component: UsersDashboardComponent, canActivate: [AuthGuard], data: {roles: ["ROLE_ADMIN"]}},
  {path: routeNames.forbidden, component: ForbiddenComponent},
  {path: routeNames.rendezVous, component: RendezVousComponent},
  {path: '**', component: NotFoundComponent},
];
