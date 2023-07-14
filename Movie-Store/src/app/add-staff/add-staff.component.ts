import { Component } from '@angular/core';

import { Router } from '@angular/router';

import { StaffService } from '../servicestaff.service';

import { Staff } from '../staff/staff';

@Component({
  selector: 'app-add-staff',

  templateUrl: './add-staff.component.html',

  styleUrls: ['./add-staff.component.css'],
})
export class AddStaffComponent {
  nameRegex = /^[A-Za-z]+$/;
  emailRegex = /^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
  staff: Staff = new Staff();

  constructor(private staffService: StaffService, private router: Router) {}

  onAddStaff() {
    console.log(this.staff);

    this.staffService.addStaff(this.staff).subscribe(() => {
      console.log('Staff created');

      this.router.navigate(['/staff']);
    });
  }
}
