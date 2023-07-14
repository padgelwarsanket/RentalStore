import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Customer } from './customer-list/customer';




@Injectable({

  providedIn: 'root',

})

export class CustomerService {

  http: any;

  private baseUrl = 'http://localhost:9090/api/customer';




  constructor(private httpclient: HttpClient) { }




  getCustomers(): Observable<Customer[]> {

    console.log('entered');

    return this.httpclient.get<Customer[]>(`${this.baseUrl}`);

  }




  getbyfirstname(searchQuery: string) {

    return this.httpclient.get<Customer[]>(

      `${this.baseUrl}/firstname/${searchQuery}`

    );

  }

  getbylastname(searchQuery: string) {

    return this.httpclient.get<Customer[]>(

      `${this.baseUrl}/lastname/${searchQuery}`

    );

  }




  getbyinactivecustomer(searchQuery: string) {

    return this.httpclient.get<Customer[]>(`${this.baseUrl}/inactive`);

  }




  getbyactivecustomer(searchQuery: string) {

    return this.httpclient.get<Customer[]>(`${this.baseUrl}/active`);

  }




  getbycountry(searchQuery: string) {

    return this.httpclient.get<Customer[]>(

      `${this.baseUrl}/country/${searchQuery}`

    );

  }




  getbycity(searchQuery: string) {

    return this.httpclient.get<Customer[]>(

      `${this.baseUrl}/city/${searchQuery}`

    );

  }




  getbyemail(searchQuery: string) {

    return this.httpclient.get<Customer[]>(

      `${this.baseUrl}/email/${searchQuery}`

    );

  }




  getbyphonenumber(searchQuery: string) {

    return this.httpclient.get<Customer[]>(

      `${this.baseUrl}/phone/${searchQuery}`

    );

  }




  addCustomer(customer: Customer): Observable<any> {

    console.log('in customer');




    return this.httpclient.post(this.baseUrl, customer);

  }




  // updateCustomerFirstName(customerId: number, firstName: string): Observable<any> {




  //   const url = `${this.baseUrl}/update/fn/${customerId}`;

  //   return this.httpclient.put(url,  {firstName} );

  // }




  // updateCustomerLastName(customerId: number, lastName: string): Observable<any> {

  //   const url = `${this.baseUrl}/update/ln/${customerId}`;

  //   return this.httpclient.put(url, {lastName} );

  // }




  // updateCustomerEmail(customerId: number, email: string): Observable<any> {

  //   const url = `${this.baseUrl}/update/email/${customerId}`;

  //   return this.httpclient.put(url, { email });

  // }




  updateCustomer(

    customerId: number,




    storeId: number,




    firstName: string,




    lastName: string,




    addressId: number,




    email: string,
    active:any

  ): Observable<any> {

    let url = `http://localhost:9090/api/customer/update/${customerId}`;




    const body = {

      customerId: customerId,




      storeId: storeId,




      firstName: firstName,




      lastName: lastName,




      addressId: addressId,




      email: email,
      active:active,

    };
    console.log('Updating customer: ', body);
    //this.http
    return this.httpclient.put(url, body);

  }




  // updateCustomerStore(customerId: number, storeId: number): Observable<any> {

  //   const url = `${this.baseUrl}/update/store/${customerId}`;

  //   return this.httpclient.put(url, storeId);

  // }




  // updateCustomerPhone(customerId: number, phone: string): Observable<any> {

  //   const url = `${this.baseUrl}/update/phone/${customerId}`;

  //   return this.httpclient.put(url, { phone });

  // }

}