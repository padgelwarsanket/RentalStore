import { Component, OnInit } from '@angular/core';
import { StaffService } from '../servicestaff.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css'],
})
export class StaffComponent implements OnInit {
  searchAttribute!: string;
  searchQuery!: string;
  staffList: any[] = [];
  originalstafflist: any[] = [];

  constructor(private staffService: StaffService) {}
  ngOnInit(): void {
    this.staffService.getAllStaffList().subscribe((data: any) => {
      this.staffList = data;
      this.originalstafflist = data;

      console.log(this.staffList);
    });
  }

  search(): void {
    console.log('Before search:', this.staffList);
    if (this.searchQuery === '') {
      console.log('in true blog');

      this.staffList = this.originalstafflist;
    } else {
      console.log('in false blog');

      switch (this.searchAttribute) {
        case 'lastName':
          this.staffService.searchStaffByLastName(this.searchQuery).subscribe(
            (result: any[]) => {
              this.staffList = result;
            },
            (error: any) => {
              console.error(error);
            }
          );
          break;
        case 'firstName':
          this.staffService.searchStaffByFirstName(this.searchQuery).subscribe(
            (result: any[]) => {
              this.staffList = result;
            },
            (error: any) => {
              console.error(error);
            }
          );
          break;
          case 'email':
            this.staffService.searchStaffByEmail(this.searchQuery).subscribe(
              (result: any) => {
                this.staffList = result;
              },
              (error: any) => {
                console.error(error);
              }
            );
          break;
        case 'city':
          this.staffService.searchStaffByCity(this.searchQuery).subscribe(
            (result: any[]) => {
              this.staffList = result;
            },
            (error: any) => {
              console.error(error);
            }
          );
          break;
        case 'country':
          this.staffService.searchStaffByCountry(this.searchQuery).subscribe(
            (result: any[]) => {
              this.staffList = result;
            },
            (error: any) => {
              console.error(error);
            }
          );
          break;
        case 'phone':
          this.staffService.searchStaffByPhone(this.searchQuery).subscribe(
            (result: any[]) => {
              this.staffList = result;
            },
            (error: any) => {
              console.error(error);
            }
          );
          break;
        default:
          // Invalid search attribute, reset staff list
          this.staffList = [];
          break;
      }
    }
  }
}
