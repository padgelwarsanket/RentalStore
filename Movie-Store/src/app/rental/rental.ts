export class Rental  {
    rentalId! : number;
    rentalDate! : any;
    inventoryId! : number;
    customerId! : number;
    returnDate! : any;
    staffId! : number;
    lastupDate : any;

    constructor(){
        this.rentalId = 0;
    this.rentalDate = " ";
    this.inventoryId = 0;
    this.customerId = 0;
    this.returnDate = " ";
    this.staffId = 0;
    this.lastupDate = " ";
    }
}