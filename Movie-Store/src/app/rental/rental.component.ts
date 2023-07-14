import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
import { RentalService } from '../rental.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';


@Component({
  selector: 'app-rental',
  templateUrl: './rental.component.html',
  styleUrls: ['./rental.component.css']
})
export class RentalComponent implements OnInit {
  rentals: any[] = [];
  customerId!: number;

  constructor(private rentalService: RentalService , private router: Router,  private route: ActivatedRoute) { }

  ngOnInit() {
    this.fetchRentals();

    // this.route.queryParams.subscribe(params => {
    //   this.customerId = params['customerId'];
    // });
  }

  proceedToPayment() {
    this.router.navigate(['/payment'], { queryParams: { customerId: this.customerId } });
  }

  fetchRentals() {
    this.rentalService.getRentals().subscribe((data) => {
      this.rentals = data;
    });
  }

  rentFilm() {
    this.router.navigate(['/rentreceipt']);
  }
}
