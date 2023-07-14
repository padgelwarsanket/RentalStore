import { Component } from '@angular/core';
import { Customer } from '../customer-list/customer';
import { CustomerService } from '../customer.service';


@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
})
export class AddCustomerComponent {
  nameRegex = /^[A-Za-z]+$/;
  emailRegex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
  customer: Customer = new Customer();

  constructor(private customerService: CustomerService) {}

  onAddCustomer() {
    console.log(this.customer);
    this.customerService.addCustomer(this.customer).subscribe(() => {
      console.log('Customer created');
    });
  }
}
