import {Component, OnInit} from '@angular/core';
import {IPatientRegister} from "./PatientModel/ipatient-register";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Component({
  selector: 'app-rendez-vous',
  templateUrl: './rendez-vous.component.html',

})
export class RendezVousComponent implements OnInit{

  BACKEND_URL = `${environment.apiURL}/api/patient/`;

  formRegister:IPatientRegister ={
    nom:"",
    prenom:"",
    email:"",
    cin:"",
    mdp:""
};
  constructor(private http:HttpClient){}
  ngOnInit(): void {}
  onSubmit(): void {
    this.http.post(this.BACKEND_URL +"create", this.formRegister).subscribe(
      (response) => {
        console.log("Patient has been registered successfully", response);
      }
    );
  }


}
