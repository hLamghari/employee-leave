import { Injectable } from '@angular/core';
import { User } from '../core/models/user';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authUrl = environment.API_BASE_URL + '/v1/auth';
  private tokenKey = 'auth_token';
  private jwtHelper = new JwtHelperService();


  constructor(private http: HttpClient) {
  }

  login(user : User){
    return this.http.post(`${this.authUrl}/login`, user);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }

  setAuthToken(autToken : string){
    localStorage.setItem(this.tokenKey, autToken);
  }
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token && !this.jwtHelper.isTokenExpired(token);
  }

  getUserUid(){
    const token = this.getToken();
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.userUid || null;
    }
    return null;
  }
}