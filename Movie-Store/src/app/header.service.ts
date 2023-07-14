import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  private apiUrl = 'http://localhost:9090/api/actors/toptenbyfilmcount';

  constructor(private http: HttpClient) { }

  getTopTenActors(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
