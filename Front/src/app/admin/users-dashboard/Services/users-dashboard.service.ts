import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class UsersDashboardService {
  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<any>{
    return this.http.get(`${environment.apiURL}/api/user/all`)
  }

  deleteUserById(id: number): Observable<any> {
    return this.http.delete(`${environment.apiURL}/api/user/delete/${id}`)
  }

  updateUser(userId: number | undefined, userData: {
    role: string | null | undefined;
    nom: string | null | undefined;
    prenom: string | null | undefined;
    email: string | null | undefined
  }): Observable<any> {
    let UserRequest = {
      nom: userData.nom,
      prenom: userData.prenom,
      email: userData.email,
      role: userData.role,
    }
    return this.http.put(`${environment.apiURL}/api/user/update/${userId}`, UserRequest);
  }

  searchUser(query: any) : Observable<any> {
    return this.http.get(`${environment.apiURL}/api/user/search?query=${query}`)
  }

  addUser(userData: { password: string; nom: string; prenom: string; email: string }) {
    let UserRequest = {
      nom: userData.nom,
      prenom: userData.prenom,
      email: userData.email,
      password: userData.password,
    }
    return this.http.post(`${environment.apiURL}/api/user/create`, UserRequest);
  }
}
