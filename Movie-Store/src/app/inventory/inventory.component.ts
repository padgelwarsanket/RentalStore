import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Serviceinventory } from '../serviceinventory.service';
import { Inventory, Inventoryone, Inventorytwo } from './inventory';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
})
export class InventoryComponent implements OnInit {
  
  // inventorys: Inventory[] = [];
  Inventorylist!: Inventoryone[];
  orignalinventorylist!: Inventoryone[];
  option!: number;
  searchQuery!: string;
  // filmIdip! = number;
  startYear: string = '';

  endYear: string = '';

  searchAttribute: any;
  storeId!: number;
  p:number = 1;

  constructor(private inventoryService: Serviceinventory) {}

  ngOnInit(): void {
    this.getAllInventory();
  }

  getAllInventory(): void {
    this.inventoryService.getAllInventory().subscribe(
      (inventoryList: Inventoryone[]) => {
        this.Inventorylist = inventoryList;
        this.orignalinventorylist = inventoryList;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  search(): void {
    if (this.searchQuery === '') {
      this.Inventorylist = this.orignalinventorylist;
    } else {
      this.Inventorylist = this.orignalinventorylist.filter((Inventoryone) => {
        switch (this.searchAttribute) {
          case '1':
            return Inventoryone.inventoryId === parseInt(this.searchQuery, 10);
          case '2':
            return (
              Inventoryone.storeId === parseInt(this.searchQuery.toLowerCase())
            );
          case '3':
            return (
              Inventoryone.filmId === parseInt(this.searchQuery.toLowerCase())
            );
          case '4':
            return (
              Inventoryone.filmId.toString() === this.searchQuery ||
              Inventoryone.storeId.toString() === this.searchQuery
            );
          default:
            return false;
        }
      });
    }
  }

  inventoryListtwo: Inventorytwo[] = [];
  filmId!: number;
  getInventoryByStore() {
    if (this.filmId) {
      this.inventoryService.getInventoryByStoreId(this.filmId).subscribe(
        (inventoryList) => {
          this.inventoryListtwo = this.inventoryListtwo;
        },
        (error) => {
          console.error('Error fetching inventory:', error);
        }
      );
    }
  }

  getInventoryByFilm() {
    if (this.filmId) {
      this.inventoryService.getInventoryByFilmId(this.filmId).subscribe(
        (inventoryList) => {
          this.Inventorylist = inventoryList;
        },
        (error) => {
          console.error('Error fetching inventory:', error);
        }
      );
    }
  }

  getInventoryByFilmandStoreID() {
    if (this.filmId && this.storeId) {
      this.inventoryService
        .getInventoryByFilmIdAndStoreId(this.filmId, this.storeId)
        .subscribe(
          (inventoryList) => {
            this.Inventorylist = inventoryList;
            console.log(this.Inventorylist);
          },
          (error) => {
            console.error('Error fetching inventory:', error);
          }
        );
    }
  }

  currentPage: number = 1;

  rowsPerPage: number = 10;

  goToPage(pageNumber: number): void {
    const totalPages = Math.ceil(this.Inventorylist.length / this.rowsPerPage);

    if (pageNumber >= 1 && pageNumber <= totalPages) {
      this.currentPage = pageNumber;
    }
  }
}
