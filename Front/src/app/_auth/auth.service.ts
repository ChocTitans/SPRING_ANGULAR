import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  public setRole(role: string) {
    localStorage.setItem('role', role);
  }

  public getRole() : string | null {
    return localStorage.getItem('role') || null;
  }

  public setJwtToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getJwtToken() : string | null {
    return localStorage.getItem('jwtToken') || null;
  }

  public setRefreshToken(refreshToken: string) {
    localStorage.setItem('refreshToken', refreshToken);
  }

  public getRefreshToken() : string | null {
    return localStorage.getItem('refreshToken') || null;
  }

  public clear() {
    localStorage.removeItem('role');
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('refreshToken');
  }

  public isAuthenticated() : boolean {
    return !!(this.getJwtToken() && this.getRole());
  }
}
