import { Component, OnInit } from '@angular/core';

import { servicestore } from '../servicestore.service';

import { Store } from './storefilm';

@Component({
  selector: 'app-storefilm',

  templateUrl: './storefilm.component.html',

  styleUrls: ['./storefilm.component.css'],
})
export class StoreListComponent implements OnInit {
  storelist: Store[] = [];

  searchAttribute: string = '';

  searchQuery: string = '';

  allStoreList: Store[] = [];

  constructor(private storeService: servicestore) {}

  ngOnInit(): void {
    this.storeService.getAllStores().subscribe((data: any) => {
      this.storelist = data;

      console.log(this.storelist);
    });
  }

  search(): void {
    if (this.searchQuery === '') {
      console.log('if');

      this.storelist = this.allStoreList;
    } else {
      {
        switch (this.searchAttribute) {
          case 'City':
            this.storeService

              .getStoreByCity(this.searchQuery)

              .subscribe((data: any) => (this.storelist = [...data]));

            break;

          case 'Country':
            this.storeService

              .getStoreByCountry(this.searchQuery)

              .subscribe((data: any) => (this.storelist = [...data]));

            break;

          case 'phonenumber':
            this.storeService

              .getStoreByphone(this.searchQuery)

              .subscribe((data: Store[]) => {
                this.storelist = [];

                this.storelist.push(...data);
              });

            break;

          default:

          // return false;
        }
      }
    }
  }
}
