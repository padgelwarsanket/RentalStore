import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Rental } from './rental/rental';

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  private apiUrl = 'http://localhost:9090/api/rental';

  constructor(private httpClient: HttpClient) { }

  getRentals(): Observable<Rental[]> {
    console.log("i service");
    return this.httpClient.get<Rental[]>(`${this.apiUrl}`);
  }

  addRental(actors: any): number {
    console.log("Inside actors");
    const rental: any = this.httpClient.post(`${this.apiUrl}/add`, actors).subscribe(() => {
      console.log('created');


    });
    return rental.rentalId;
  }
}
