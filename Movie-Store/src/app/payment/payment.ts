export class Payment {

    paymentId: number;

    customerId: number;

    staffId: number;

    rentalId: number;

    amount: number;

    paymentDate: any;

    lastUpdate: any;




    constructor(paymentId: number, customerId: number, staffId: number, rentalId: number, amount: number, paymentDate: Date, lastUpdate: Date ,revenue:any , store_Id : any )

    {

        this.paymentId = paymentId

        this.customerId = customerId

        this.staffId = staffId

        this.rentalId = rentalId

        this.amount = amount

        this.paymentDate = paymentDate

        this.lastUpdate = lastUpdate





    }




}