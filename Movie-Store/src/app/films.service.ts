import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Films } from './film-list/films';


@Injectable({
  providedIn: 'root',
})
export class FilmsService {
  private baseurl = 'http://localhost:9090/api';
  private posturl = 'http://localhost:9090/api/films';
  private apiUrl = 'http://localhost:9090/api/films/update';
  http: any;

  constructor(private httpClient: HttpClient) {}

  getAllFilmList(): Observable<Films[]> {
    return this.httpClient.get<Films[]>(`${this.baseurl}/films/all`);
  }

  addfilm(film: any): Observable<object> {
    console.log(film);
    return this.httpClient.post(`${this.posturl}`, film);
  }
  getbytitle(title: string) {
    return this.httpClient.get<Films[]>(`${this.baseurl}/films/title/${title}`);
  }
  getbyreleaseyear(releaseYear: string) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/year/${releaseYear}`
    );
  }
  rentalrategreaterthan(rentalrategreaterthan: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/rate/gt/${rentalrategreaterthan}`
    );
  }

  rentalratelowerthan(rentalratelowerthan: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/rate/lt/${rentalratelowerthan}`
    );
  }

  lengthgraeater(lengthgraeater: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/length/gt/${lengthgraeater}`
    );
  }
  lengthlower(lengthlower: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/length/lt/${lengthlower}`
    );
  }

  rentalDurationgreaterthan(rentalDurationgreaterthan: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/duration/gt/${rentalDurationgreaterthan}`
    );
  }

  rentalDurationlowerthan(rentalDurationlowerthan: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/duration/lt/${rentalDurationlowerthan}`
    );
  }

  releaseYearRange(startYear: string, endYear: string) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/betweenyear/${startYear}/${endYear}`
    );
  }

  ratinggreaterthan(ratinggreaterthan: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/rating/gt/${ratinggreaterthan}`
    );
  }
  ratinglowerthan(ratinglowerthan: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/films/rating/gt/${ratinglowerthan}`
    );
  }

  countFilmsByYear(): Observable<{ [key: string]: number }> {
    return this.httpClient.get<{ [key: string]: number }>(
      `${this.baseurl}/films/countbyyear`
    );
  }

  getfilmofactorbyactorid(actorId: any) {
    return this.httpClient.get<Films[]>(
      `${this.baseurl}/actors/${actorId}/films`
    );
  }

  // update film by film Id

  // updateFilmByTitle(filmId: number, title: string): Observable<any> {
  //   console.log({title});

  //   const url = `${this.apiUrl}/title/${filmId}`;
  //   return this.httpClient.put(url, { title });
  // }

  // updateFilmByReleaseYear(filmId: number, releaseYear: string): Observable<any> {
  //   const url = `${this.apiUrl}/releaseyear/${filmId}`;
  //   return this.httpClient.put(url, { releaseYear });
  // }

  // updateFilmByRentalDuration(filmId: number, rentalDuration: number): Observable<any> {
  //   const url = `${this.apiUrl}/rentalduration/${filmId}`;
  //   return this.httpClient.put(url, { rentalDuration });
  // }

  // updateFilmByRentalRate(filmId: number, rentalRate: number): Observable<any> {
  //   const url = `${this.apiUrl}/rentalrate/${filmId}`;
  //   return this.httpClient.put(url, { rentalRate });
  // }

  // updateFilmByRating(filmId: number, rating: string): Observable<any> {
  //   const url = `${this.apiUrl}/rating/${filmId}`;
  //   return this.httpClient.put(url, { rating });
  // }

  // updateFilmByLanguage(filmId: number, languageId: number): Observable<any> {
  //   const url = `${this.apiUrl}/language/${filmId}`;
  //   return this.httpClient.put(url, {languageId});
  // }

  updateFilmByTitle(filmId: number, title: string): Observable<any> {
    console.log({ title });

    const url = `${this.apiUrl}/title/${filmId}`;

    return this.httpClient.put(url, { title });
  }

  updateFilmByReleaseYear(
    filmId: number,
    releaseYear: string
  ): Observable<any> {
    console.log({ releaseYear });

    const url = `${this.apiUrl}/releaseyear/${filmId}`;

    return this.httpClient.put(url, { releaseYear });
  }

  updateFilmByRentalDuration(
    filmId: number,
    rentalDuration: number
  ): Observable<any> {
    console.log({ rentalDuration });

    const url = `${this.apiUrl}/rentalduration/${filmId}`;

    return this.httpClient.put(url, { rentalDuration });
  }

  updateFilmByRentalRate(filmId: number, rentalRate: number): Observable<any> {
    console.log({ rentalRate });

    const url = `${this.apiUrl}/rentalrate/${filmId}`;

    return this.httpClient.put(url, { rentalRate });
  }

  updateFilmByRating(filmId: number, rating: string): Observable<any> {
    const url = `${this.apiUrl}/rating/${filmId}`;

    return this.httpClient.put(url, { rating });
  }

  updateFilmByLanguage(filmId: number, languageId: number): Observable<any> {
    const url = `${this.apiUrl}/language/${filmId}`;

    return this.httpClient.put(url, { languageId });
  }
}
