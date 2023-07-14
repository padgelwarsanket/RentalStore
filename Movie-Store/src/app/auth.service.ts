import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:9090/api/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private myUsername = '';
  constructor(private http: HttpClient) {}
  login(username: string, password: string): Observable<any> {
    this.myUsername = username;
    return this.http.post(
      AUTH_API + 'signin',

      {
        username,

        password,
      },

      httpOptions
    );
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',

      {
        username,

        email,

        password,
      },

      httpOptions
    );
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', {}, httpOptions);
  }

  getUsername() {
    return this.myUsername;
  }
}
