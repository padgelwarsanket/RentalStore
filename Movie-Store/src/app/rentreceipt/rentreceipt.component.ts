import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rentreceipt',
  templateUrl: './rentreceipt.component.html',
  styleUrls: ['./rentreceipt.component.css']
})
export class RentreceiptComponent {

  rentReceipt: any = {
    staffId: null,
    customerId: null,
    inventoryId: null
  };

  constructor(private router: Router) {}

  submitRentReceiptForm() {
    // Handle form submission logic here
    // Redirect to the payment page with the rent receipt data
    this.router.navigate(['/payment'], { queryParams: this.rentReceipt });
  }

}
