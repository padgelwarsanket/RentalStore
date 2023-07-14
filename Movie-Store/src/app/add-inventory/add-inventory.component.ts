import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Inventory } from '../inventory/inventory';
import { Serviceinventory } from '../serviceinventory.service';

@Component({
  selector: 'app-add-inventory',
  templateUrl: './add-inventory.component.html',
  styleUrls: ['./add-inventory.component.css'],
})
export class AddInventoryComponent {
  newInventory: Inventory = {
    inventoryId: 0,
    filmId: 0,
    storeId: 0,
    lastUpdate: ''
  };

  constructor(private inventoryService: Serviceinventory) {}

  addInventory() {
    this.inventoryService.addInventory(this.newInventory).subscribe(
      (response: any) => {
        console.log('Inventory added successfully:', response);
        // Reset the form after successful submission
        this.newInventory = {
          inventoryId: 0,
          filmId: 0,
          storeId: 0,
          lastUpdate: ''
        };
      },
      (error: any) => {
        console.log('Error occurred while adding inventory:', error);
      }
    );
  }
}
