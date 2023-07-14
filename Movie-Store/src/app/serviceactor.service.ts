import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { actor } from './actor/actor';
import { Actor } from './header/Actor';

@Injectable({
  providedIn: 'root',
})
export class ServiceactorService {
  private baseurl = 'http://localhost:9090/api/actors';
  private apiUrl = 'http://localhost:9090/api/actors/update';
  private topapiUrl = 'http://localhost:9090/api/actors/toptenbyfilmcount';
  private filmid = 'http://localhost:9090/api/films';

  constructor(private httpClient: HttpClient) {}

  getTopActors(): Observable<Actor[]> {
    return this.httpClient.get<Actor[]>(this.topapiUrl);
  }

  getAllActor() {
    console.log('getallactoeservice');

    return this.httpClient.get('http://localhost:9090/api/actors/all');
  }

  addActor(actors: any): Observable<any> {
    console.log(actors);
    return this.httpClient.post(`${this.baseurl}`, actors);
  }

  getbyactorfirstname(firstName: string) {
    return this.httpClient.get<actor[]>(
      `${this.baseurl}/firstname/${firstName}`
    );
  }

  getbyactorlastname(lastName: string) {
    return this.httpClient.get<actor[]>(`${this.baseurl}/lastname/${lastName}`);
  }

  getactorbyfilmbyfilmid(actorId: any) {

    return this.httpClient.get<actor[]>(`${this.filmid}/${actorId}/actors`);

  }

  updateActorByFirstName(actorId: number, firstName: string): Observable<any> {
    const url = `${this.apiUrl}/firstname/${actorId}`;
    return this.httpClient.put(url, { firstName });
  }
  updateActorByLastName(actorId: number, lastName: string): Observable<any> {
    const url = `${this.apiUrl}/lastname/${actorId}`;

    console.log(lastName);

    return this.httpClient.put(url, { lastName });
  }
}
