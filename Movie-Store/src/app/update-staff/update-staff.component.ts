import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StaffService } from '../servicestaff.service';
import { Staff } from '../staff/staff';

@Component({
  selector: 'app-update-staff',
  templateUrl: './update-staff.component.html',
  styleUrls: ['./update-staff.component.css'],
})
export class UpdateStaffComponent implements OnInit {
  // staffId: number = 0;
  // firstName: string = "";
  // lastName: string = "";
  // email: string = "";
  // city: string = "";
  // country: string = "";
  // phone: string = "";

  // constructor(private staffService: StaffService) {}

  // ngOnInit(): void {}

  // updateStaff() {
  //   if (this.staffId != 0 ) {
  //     if (this.firstName != "") {
  //       console.log(this.staffId);
  //       console.log(this.firstName);

  //       this.staffService.updateFirstName(this.staffId, this.firstName).subscribe(
  //         () => {
  //           console.log('First Name updated successfully');
  //         },
  //         (error) => {
  //           console.error('Failed to update First Name', error);
  //           alert('Failed to update First Name');
  //         }
  //       );
  //     }

  //     if (this.lastName) {
  //       this.staffService.updateLastName(this.staffId, this.lastName).subscribe(
  //         (response) => {
  //           console.log('Last Name updated successfully');
  //         },
  //         (error) => {
  //           console.error('Failed to update Last Name', error);
  //           alert('Failed to update Last Name');
  //         }
  //       );
  //     }

  //     if (this.email) {
  //       this.staffService.updateEmail(this.staffId, this.email).subscribe(
  //         (response) => {
  //           console.log('Email updated successfully');
  //         },
  //         (error) => {
  //           console.error('Failed to update Email', error);
  //           alert('Failed to update Email');
  //         }
  //       );
  //     }

  //     // if (this.phone) {
  //     //   this.staffService.updatePhone(this.staffId, this.phone).subscribe(
  //     //     (response) => {
  //     //       console.log('Phone updated successfully');
  //     //     },
  //     //     (error) => {
  //     //       console.error('Failed to update Phone', error);
  //     //       alert('Failed to update Phone');
  //     //     }
  //     //   );
  //     // }

  //     alert('Staff updated successfully');
  //   } else {
  //     alert('Please provide a valid Staff ID');
  //   }
  // }

  staffId = 0;

  firstName = '';

  lastName = '';

  addressId = '';

  email = '';

  storeId = 0;

  active = '';

  constructor(
    private staffService: StaffService,

    private routerr: ActivatedRoute,

    private router: Router
  ) {}

  ngOnInit(): void {
    this.staffId = this.routerr.snapshot.params['staffId'];
  }

  // let resp:=this.staffService.getById(this.staffId);

  // resp.subscribe(data =>{

  //   this.staffId=data.staffId;

  //   this.firstName= data.firstName;

  //   this.lastName= data.lastName;

  //   this.addressId= data.addressId;

  //   this.email= data.email;

  // })

  updateStaff() {
    this.staffService
      .updatestaff(
        this.staffId,

        this.firstName,

        this.lastName,

        this.addressId,

        this.email,

        // this.storeId,

        // this.active
      )

      .subscribe(
        (response) => {
          console.log('Staff updated successfully');

          const message = response.message;

          alert('Staff updated successfully');

          this.goToStaffList();
        },

        (error) => {
          console.log('Error Updaing Staff', error);
        }
      );
  }

  onSubmit() {
    this.updateStaff();

    // this.router.navigate(['/staff']);

    console.log(this.updateStaff);
  }

  goToStaffList() {
    // this.router.navigate(["/staff"]);
  }
}
