import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';

import { servicestore } from '../servicestore.service';

import { Store } from '../storelist/storefilm';





@Component({

  selector: 'app-add-store',

  templateUrl: './add-store.component.html',

  styleUrls: ['./add-store.component.css']

})

export class AddStoreComponent implements OnInit {

  store: Store = new Store();




  constructor(private storeService: servicestore, private router: Router) { }

  ngOnInit(): void {

    // throw new Error('Method not implemented.');

  }




  onAddStore() {

    console.log(this.store);

    this.storeService.addStore(this.store).subscribe(() => {

      console.log('Store created');

    });

    this.router.navigate(['/storelist']);

  }






}