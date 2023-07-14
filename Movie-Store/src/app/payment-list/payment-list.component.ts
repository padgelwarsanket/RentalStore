import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { PaymentService } from '../payment.service';
import { Payment } from '../payment/payment';


interface RevenueData {
  Store_Id: number;
  Revenue: number;
}

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css'],
})
export class PaymentListComponent implements OnInit {
  headers: any[] = [];
  payments: any[] = [];
  payment: Payment[] = [];
  p: number = 1;
  searchAttribute: any;
  storeId: number = 0;
  filmId: number = 1;
  public staffId!: number;
  revenueData!: any[] ;
  searchQuery: string = '';
  orignalpayments: Payment[] = [];
  dateWiseTable: boolean = false;
  paymentTable: boolean = true;
  aStore:boolean = false;
  storeWise = false;
  filmsByStore = false;

  constructor(
    private paymentService: PaymentService,
    private dataService: DataService
  ) {}

  ngOnInit() {
    console.log('in ng on it');
    this.staffId = this.dataService.getStaffId();

    this.fetchPayments();
  }
  // getRevenue(filmId: number) {
  //   this.paymentService.getRevenueFilmById(filmId).subscribe(
  //     (data) => {
  //       this.revenueData = data;
  //     },
  //     (error) => {
  //       console.log('An error occurred:', error);
  //     }
  //   );
  // }

  fetchPayments() {
    console.log('in fetch ');

    // this.paymentService.getAllPayments().subscribe((payments) => {
    //   this.payments = payments;
    //   this.orignalpayments = payments;

    // });
  }
  search() {
    if (this.searchQuery === '' ) {
      console.log("in true blog");
      
      this.payments = this.orignalpayments;
    } else
    console.log("in else");
    
    switch (this.searchAttribute) {
      // case 'CumulativeRevenueOfAllStores':
      //   this.paymentService.getRevenueDateWise().subscribe((revenue) => {
      //     console.log('Cumulative Revenue of All Stores:', revenue);
      //     // Handle the revenue data
      //   });
      //   break;
      case 'CumulativeRevenueDateWise':
      console.log("CumulativeRevenueOfAStore");
        this.headers = ["Payment Date","Amount","Sum"];
        this.paymentService
        .getRevenueDateWise()
        .subscribe((revenue) => {
          console.log('Cumulative Revenue of A Store:', revenue);
          (this.payments = [...revenue])
          });
          this.paymentTable = false;
          this.dateWiseTable = true;
          this.aStore = false;
          this.storeWise = false;
          this.filmsByStore = false;
        break;
      case 'CumulativeRevenueOfAStore':
      console.log("CumulativeRevenueOfAStore");
        this.headers = ["Revenue","Store","Date"]
        this.paymentService
        .getRevenueStoreWise(this.storeId)
        .subscribe((revenue) => {
          console.log('Cumulative Revenue of A Store:', revenue);
          (this.payments = [...revenue])
          });
          this.paymentTable = false;
          this.dateWiseTable = false;
          this.aStore = true;
          this.storeWise = false;
          this.filmsByStore = false;

        break;
      // case 'RevenueOfAllFilms':
      //   this.paymentService.getRevenueFilmWise().subscribe((revenue) => {
      //     console.log('Revenue of All Films:', revenue);
      //   });
      //   break;
      case 'CumulativerevenueofaFilmStorewise':
        this.headers=["Store Id","Revenue"]
        this.paymentService
          .getRevenueFilmById(this.filmId)
          .subscribe((revenue) => {
            console.log('Cumulative Revenue of Film Store-wise:', revenue);
            // Handle the revenue data
            (this.payments = [...revenue])
          });
          this.paymentTable = false;
          this.dateWiseTable = false;
          this.aStore = false;
          this.storeWise = true;
          this.filmsByStore = false;
        break;
      case 'CumulativerevenueofallFilmsbyStore':
        // Implement logic to fetch cumulative revenue of all films by store
        break;
      default:
        // Handle other cases
        break;
    }
  }
}
