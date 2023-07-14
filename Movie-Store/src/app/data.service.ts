import { Injectable } from '@angular/core';

import { forkJoin } from 'rxjs';

import { RentalService } from './rental.service';

import { Serviceinventory } from './serviceinventory.service';




@Injectable({

  providedIn: 'root'

})

export class DataService {

  private filmData: Map<string, number> = new Map<string, number>();

  private selectedIds:number[]=[];

  private staffId = 1;

  private customerId = 1;
  private username!:string;
  index=0;

  constructor(private inventoryService : Serviceinventory, private rentalService : RentalService) {}




  setStaffId(staffId:number){

    this.staffId = staffId;

  }

  setUsername(form:any){

    this.username =  form.username;
  
    console.log(this.username);
  
    console.log(form.username);
  
   
  
   
  
  }
  
  
  
  
  getUsername(){
  
    return this.username;
  
  }

  setCustomerId(customerId : number){

    this.customerId = customerId;




  }

  setSelectedIds(filmId:number){

    this.selectedIds[this.index]=filmId;
    
    this.index++;

  }
  nullyfyIds(){
    this.selectedIds=[];
  }
  setSelectedFilmAndRate(title: string, rentalRate: number) {

    if (this.filmData.has(title)) {

      const currentRentalRate = this.filmData.get(title) || 0;

      this.filmData.set(title, currentRentalRate + rentalRate);

    } else {

      this.filmData.set(title, rentalRate);

    }

  }

  getStaffId(){

    return this.staffId;

  }

  getCustomerId(){

    return this.customerId;

  }

  getSelectedId(){

    return this.selectedIds;

  }

  getSelectedFilmTitles() {

    return Array.from(this.filmData.keys());

  }




  getRentalRateForFilm(title: string) {

    return this.filmData.get(title) || 0;

  }




  getTotalRentalRate() {

    let totalRate = 0;

    for (const rate of this.filmData.values()) {

      totalRate += rate;

    }

    return totalRate;

  }




  setInventoryList(selectedId:number[]) :Promise<number[]> {

    return new Promise<number[]>((resolve, reject) => {

    const observables = selectedId.map(value => this.inventoryService.getSingleInventory(value));

    forkJoin(observables).subscribe(

    (data: number[]) => {

      resolve(data);

    },

    (error) => {

      console.error('Error occurred:', error);

      reject(error);

    }

    );

  });

}




setRentalList(rentalId: number[]) :Promise<number[]> {

  console.log('inside setRentalList');

  return new Promise<number[]>((resolve, reject) => {

  const observables = rentalId.map(value => this.rentalService.addRental(value));

  forkJoin(observables).subscribe(

  (data: number[]) => {

    console.log("Rental list", data);

    resolve(data);

  },

  (error) => {

    console.error('Error occurred:', error);

    reject(error);

  }

  );

});

}

}