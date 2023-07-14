import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { PaymentService } from '../payment.service';
import { RentalService } from '../rental.service';
import { Rental } from '../rental/rental';
import { Serviceinventory } from '../serviceinventory.service';
import { Payment } from './payment';

@Component({
  selector: 'app-payment',

  templateUrl: './payment.component.html',

  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  
  selectedFilmTitles: string[] = [];
  totalRentalRate: number = 0;
  public staffId!: number;
  public customerId!: number;
  private selectedId: number[] = [];
  private filmInventoryList: number[] = [];
  private filmRentalList: number[] = [];
  private filmPaymentList: number[] = [];
  private indexInv = 0;

  rental: Rental = {
    rentalId: 0,
    rentalDate: '',
    inventoryId: 0,
    customerId: 0,
    returnDate: '',
    staffId: 0,
    lastupDate: '',
  };

  payment: Payment = {
    paymentId: 0,
    lastUpdate: '',
    rentalId: 0,
    customerId: 0,
    paymentDate: '',
    staffId: 0,
    amount: 123,
  };

  constructor(
    private route: ActivatedRoute,
    private paymentService: PaymentService,
    private dataService: DataService,
    private inventoryService: Serviceinventory,
    private rentalService: RentalService,
    private router: Router
  ) {}

  ngOnInit() {
    console.log('Inside on init');

    this.selectedFilmTitles = this.dataService.getSelectedFilmTitles();

    this.totalRentalRate = this.dataService.getTotalRentalRate();

    this.customerId = this.dataService.getCustomerId();

    this.staffId = this.dataService.getStaffId();

    this.selectedId = this.dataService.getSelectedId();
    console.log('Selected ids', this.selectedId);
  }

  getRentalRateForFilm(title: string) {
    return this.dataService.getRentalRateForFilm(title);
  }

  submitPaymentForm() {
    // Call the payment service to submit the payment details
    console.log('Inside submit payment');

    this.rental.customerId = this.customerId;

    this.rental.staffId = this.staffId;

    // this.payment.customerId = this.customerId;

    // this.payment.staffId = this.staffId;

    for (let val of this.selectedId) {
      console.log('Inside for loop');

      this.inventoryService.getSingleInventory(val).subscribe((data) => {
        this.filmInventoryList[this.indexInv] = data;
        console.log('Inventory Id' + data);

        this.rental.inventoryId = data;
        console.log('Rental Data' + this.rental);

        this.filmRentalList[this.indexInv] = this.paymentService.addPayment(
          this.rental
        );

       console.log('inventory list', this.filmInventoryList);

        console.log('Rental list', this.filmRentalList);

        console.log('Payment list', this.filmPaymentList);

        this.indexInv++;
      });
    
    }

    alert('Payment Succes'); //.subscribe(response => {

    // Handle the response as needed

    //});
    this.router.navigate(['../header'])
  }

  onSelectCustomer(fiilmInventoryList: number[]) {
    console.log('inside select customer' + fiilmInventoryList);

    this.dataService
      .setRentalList(this.filmInventoryList)

      .then((data: number[]) => {
        this.filmRentalList = data;

        console.log('Rental list', this.filmRentalList);
      })

      .catch((error) => {
        console.error('Error occurred:', error);
      });
  }

  onBuyButton() {
    // console.log(this.selectedId);
    // this.dataService.setInventoryList(this.selectedId);
    // this.dataService.setInventoryList(this.selectedId)
    //     .then((data: number[]) => {
    //       this.filmInventoryList = data;
    //       console.log("Inv list paymentcomp", this.filmInventoryList);
    //       this.onSelectCustomer(filmInventoryList[1]);
    //     })
    //     .catch((error) => {
    //       console.error('Error occurred:', error);
    //     });
    //line 2
    // console.log("Inv list", this.filmInventoryList);
  }
}
