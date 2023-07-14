import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment.service';

@Component({
  selector: 'app-view-payment',
  templateUrl: './view-payment.component.html',
  styleUrls: ['./view-payment.component.css']
})
export class ViewPaymentComponent implements OnInit {
  revenueDateWiseData: any[] = [];
  // revenueStoreWiseData: any[] = [];
  // revenueFilmWiseData: any[] = [];
  // revenueFilmByIdData: any[] = [];
  // revenueFilmsStoreWiseData: any[] = [];

  constructor(private paymentService: PaymentService) { }

  ngOnInit() {
    this.getRevenueDateWise();
    // this.getRevenueStoreWise(1); // Replace 1 with the desired storeId
    // this.getRevenueFilmWise();
    // this.getRevenueFilmById(1); // Replace 1 with the desired filmId
    // this.getRevenueFilmsStoreWise(1); // Replace 1 with the desired storeId
  }

  getRevenueDateWise() {
    this.paymentService.getRevenueDateWise().subscribe(
      data => {
        this.revenueDateWiseData = data;
      },
      error => {
        console.error(error);
      }
    );
  }

  // getRevenueStoreWise(storeId: number) {
  //   this.paymentService.getRevenueStoreWise(storeId).subscribe(
  //     data => {
  //       this.revenueStoreWiseData = data;
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //   );
  // }

  // getRevenueFilmWise() {
  //   this.paymentService.getRevenueFilmWise().subscribe(
  //     data => {
  //       this.revenueFilmWiseData = data;
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //   );
  // }

  // getRevenueFilmById(filmId: number) {
  //   this.paymentService.getRevenueFilmById(filmId).subscribe(
  //     data => {
  //       this.revenueFilmByIdData = data;
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //   );
  // }

  // getRevenueFilmsStoreWise(storeId: number) {
  //   this.paymentService.getRevenueFilmsStoreWise(storeId).subscribe(
  //     data => {
  //       this.revenueFilmsStoreWiseData = data;
  //     },
  //     error => {
  //       console.error(error);
  //     }
  //   );
  // }
}
