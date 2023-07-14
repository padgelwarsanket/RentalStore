// import { Component } from '@angular/core';
// import { CustomerService } from '../customer.service';

// @Component({
//   selector: 'app-update-customer',
//   templateUrl: './update-customer.component.html',
//   styleUrls: ['./update-customer.component.css'],
// })
// export class UpdateCustomerComponent {
//   customerId!: number ;
//   firstName!: string ;
//   lastName!: string ;
//   email!: string ;
//   storeId!: number  ;
//   phone!: string ;

//   constructor(private customerService: CustomerService) {}

//   updateCustomer() {
//     if (this.customerId) {
//       if (this.firstName) {
//         this.customerService.updateCustomerFirstName(this.customerId, this.firstName).subscribe(
//           (response) => {
//             console.log('First Name updated successfully:', this.firstName);
//             this.updateCustomerLastName();
//           },
//           (error) => {
//             console.error('Failed to update First Name', error);
//             alert('Failed to update First Name');
//           }
//         );
//       } else {
//         this.updateCustomerLastName();
//       }
//     } else {
//       alert('Please provide a valid Customer ID');
//     }
//   }
  
//   updateCustomerLastName() {
//     if (this.lastName) {
//       this.customerService.updateCustomerLastName(this.customerId, this.lastName).subscribe(
//         (response) => {
//           console.log('Last Name updated successfully');
//           this.updateCustomerEmail();
//         },
//         (error) => {
//           console.error('Failed to update Last Name', error);
//           alert('Failed to update Last Name');
//         }
//       );
//     } else {
//       this.updateCustomerEmail();
//     }
//   }
  
//   updateCustomerEmail() {
//     if (this.email) {
//       this.customerService.updateCustomerEmail(this.customerId, this.email).subscribe(
//         (response) => {
//           console.log('Email updated successfully');
//           this.updateCustomerStore();
//         },
//         (error) => {
//           console.error('Failed to update Email', error);
//           alert('Failed to update Email');
//         }
//       );
//     } else {
//       this.updateCustomerStore();
//     }
//   }
  
//   updateCustomerStore() {
//     if (this.storeId) {
//       this.customerService.updateCustomerStore(this.customerId, this.storeId).subscribe(
//         (response) => {
//           console.log('Store updated successfully');
//           this.updateCustomerPhone();
//         },
//         (error) => {
//           console.error('Failed to update Store', error);
//           alert('Failed to update Store');
//         }
//       );
//     } else {
//       this.updateCustomerPhone();
//     }
//   }
  
//   updateCustomerPhone() {
//     if (this.phone) {
//       this.customerService.updateCustomerPhone(this.customerId, this.phone).subscribe(
//         (response) => {
//           console.log('Phone updated successfully');
//           alert('Customer updated successfully');
//         },
//         (error) => {
//           console.error('Failed to update Phone', error);
//           alert('Failed to update Phone');
//         }
//       );
//     } else {
//       alert('Customer updated successfully');
//     }
//   }
  

// }

import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit{

  customerId = 0;
  storeId = 0;
  firstName = '';
  lastName = '';
  addressId = 0;
  email = '';
  active =0;

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['customerId'];
  }

  updateCustomer() {

    console.log("-----"+this.customerId);
    
    this.customerService
      .updateCustomer(
        this.customerId,
        this.storeId,
        this.firstName,
        this.lastName,
        this.addressId,
        this.email,
        this.active
      )
      .subscribe(
        (response) => {
          console.log('Customer updated successfully');
          const message = response.message;
          alert('Customer updated successfully');
          // this.goToCustomerList();
        },
        (error) => {
          console.log('Error updating customer', error);
        }
      );
  }

  onSubmit() {
    this.updateCustomer();
    // console.log(this.updateCustomer);
    this.router.navigate(['/customer-list']);

  }

  goToCustomerList() {
    // Navigate to the customer list page or any other desired page
    // this.router.navigate(['/customer']);
  }
}

