import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import {  Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { DataService } from '../data.service';
import { Customer } from './customer';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css'],
})
export class CustomerListComponent implements OnInit {
  customer: any[] = [];
  customers: Customer[] = [];
  filteredcustomer: Customer[] = [];
  title!: string;
  searchAttribute: string = '';
  searchQuery: string = '';
  orginalcustomer: any[] = [];
  customerarray : any[] = [];

  constructor(private customerService: CustomerService ,  private router: Router, private dataService : DataService) {}

  ngOnInit() {
    this.customerService.getCustomers().subscribe((data) => {
      this.customers = data;
    });
    this.getcustomer();
  }


  goToAddCustomer() {
    this.router.navigate(['/add-customer']);
  }


  proceedToPayment(customer: any) {
    // Navigate to the PaymentComponent and pass the customer information as query parameters
    const customerId = customer.customerId;
    this.dataService.setCustomerId(customerId);
    this.router.navigate(['/payment'], { queryParams: customer });
  }

  getcustomer() {
    this.customerService.getCustomers().subscribe((data) => {
      this.customers = data;
      this.orginalcustomer = data;
    });
  }
  search(): void {
    if (this.searchQuery === '') {
      this.customerService.getCustomers().subscribe((data) => {
        this.customers = data;
        
      });
    } else {
       this.customers = this.orginalcustomer.filter(() => {
        switch (this.searchAttribute) {
          case 'firstname':
            this.customerService
              .getbyfirstname(this.searchQuery)
              .subscribe((data: any) => (this.customers = [...data]));
            break;

          case 'lastname':
            this.customerService
              .getbylastname(this.searchQuery)
              .subscribe((data: any) => (this.customers = [...data]));
            break;

            case 'email': 
              this.customerService
              .getbyemail(this.searchQuery)
              .subscribe((data: any) => (this.customers = [data]));
            break;

          case 'city':
            this.customerService
              .getbycity(this.searchQuery)
              .subscribe((data: any) => (this.customers = [...data]));
            break;

          case 'country':
            this.customerService
              .getbycountry(this.searchQuery)
              .subscribe((data: any) => (this.customers = [...data]));
            break;

          case 'active':
            this.customerService
              .getbyactivecustomer(this.searchQuery)
              .subscribe((data: any) => (this.customers = [...data]));
            break;

          case 'inactivecustomer':
            this.customerService
              .getbyinactivecustomer(this.searchQuery)
              .subscribe((data: any) => (this.customers = [...data]));
            break;

          case 'phone':
            this.customerService
              .getbyphonenumber(this.searchQuery)
              .subscribe((data: any) => (this.customers = [data]));
            break;
        }
      });
    }
  }
}
