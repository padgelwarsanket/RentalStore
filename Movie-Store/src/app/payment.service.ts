import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payment } from './payment/payment';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private apiUrl = 'http://localhost:9090/api/payment';

  constructor(private http: HttpClient) {}

  addPayment(paymentData: any): number {
    console.log('Inside add payment');
    const payment: any = this.http
      .post<string>(`${this.apiUrl}/add/rental`, paymentData)
      .subscribe(() => {
        console.log('created');
      });
    return payment.paymentId;
  }

  getRevenueDateWise(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/revenue/datewise`);
  }

  //http://localhost:9090/api/payment/revenue/datewise/store/1
  getRevenueStoreWise(storeId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.apiUrl}/revenue/datewise/store/${storeId}`
    );
  }

  getRevenueFilmWise(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/revenue/filmwise`);
  }

  getRevenueFilmById(filmId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/revenue/film/${filmId}`);
  }

  getRevenueFilmsStoreWise(storeId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.apiUrl}/revenue/films/store/${storeId}`
    );
  }

  submitPayment(payment: any): Observable<any> {
    // Make an HTTP POST request to submit the payment details
    return this.http.post<any>(this.apiUrl, payment);
  }

  getAllPayments(): Observable<Payment[]> {
    console.log('in service layer');

    return this.http.get<Payment[]>(`${this.apiUrl}`);
  }
}
