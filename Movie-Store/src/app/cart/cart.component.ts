import { Component , OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  selectedFilms!: any[];

  constructor(private router: Router) { }
  ngOnInit(): void {

    const state = history.state;
    if (state && state.selectedFilms) {
      this.selectedFilms = state.selectedFilms;
    } else {
      // Handle case when accessed directly without selected films
      this.router.navigate(['/']); // Redirect to home or appropriate page
    }
    
  }

  removeFilm(film: any) {
    const index = this.selectedFilms.indexOf(film);
    if (index !== -1) {
      this.selectedFilms.splice(index, 1);
    }
  }

  checkout() {
    // Implement the checkout functionality according to your requirements
    // For example, you could redirect to a payment page or process the order
    console.log('Checkout button clicked');
  }

}
