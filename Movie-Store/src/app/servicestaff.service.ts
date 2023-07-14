import { Attribute, Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Staff } from './staff/staff';

import { query } from '@angular/animations';




@Injectable({

  providedIn: 'root',

})

export class StaffService {

  private baseUrl = 'http://localhost:9090/api/staff';




  constructor(private http: HttpClient) {}

  searchStaffByFirstName(firstName: string): Observable<any[]> {

    const url = `${this.baseUrl}/fn/${firstName}`;

    return this.http.get<any[]>(url);

  }




  searchStaffByLastName(lastname: string): Observable<any[]> {

    const url = `${this.baseUrl}/lastname/${lastname}`;

    return this.http.get<any[]>(url);

  }




  searchStaffByEmail(email: string): Observable<any> {

    const url = `${this.baseUrl}/email/${email}`;

    return this.http.get<any>(url);

  }




  // Woodridge , Lethbridge

  searchStaffByCity(city: string): Observable<any[]> {

    const url = `${this.baseUrl}/city/${city}`;

    return this.http.get<any[]>(url);

  }

  // Australia,Canada

  searchStaffByCountry(country: string): Observable<any[]> {

    const url = `${this.baseUrl}/country/${country}`;

    return this.http.get<any[]>(url);

  }

  // 14033335568 , 6172235589

  searchStaffByPhone(phone: string): Observable<any[]> {

    const url = `${this.baseUrl}/phone/${phone}`;

    return this.http.get<any[]>(url);

  }




  getAllStaffList() {

    {

      return this.http.get(this.baseUrl);

    }

  }

  addStaff(staff: Staff): Observable<any> {




    // const url =`${this.baseUrl}?attribute=${Attribute}&query=${query}`;




    return this.http.post<any>(this.baseUrl, staff);




  }




  // updateFirstName(staffId: number, firstName: string): Observable<any> {

  //   const url = `${this.baseUrl}/update/firstname/${staffId}`;

  //   return this.http.put(url, { firstName });

  // }




  // updateLastName(staffId: number, lastName: string): Observable<any> {

  //   const url = `${this.baseUrl}/update/lastname/${staffId}`;

  //   return this.http.put(url, { lastName });

  // }




  // updateEmail(staffId: number, email: string): Observable<any> {

  //   const url = `${this.baseUrl}/update/email/${staffId}`;

  //   return this.http.put(url, { email });

  // }




  updatestaff(

    staffId: number,




    firstName: string,




    lastName: string,




    addressId: string,




    email: string,




    // storeId:number,




    // active: number

  ): Observable<any> {

    const url = `http://localhost:9090/api/staff/update/${staffId}`;




    const body = {

      staffId: staffId,




      firstName: firstName,




      lastName: lastName,




      addressId: addressId,




      email: email,




      // storeId:storeId,




      // active:active

    };




    console.log('se' + body);




    return this.http.put(url, body);

  }




  updateCity(staffId: number, city: string): Observable<any> {

    const url = `${this.baseUrl}/update/city/${staffId}`;

    return this.http.put(url, { city });

  }




  updateCountry(staffId: number, country: string): Observable<any> {

    const url = `${this.baseUrl}/update/country/${staffId}`;

    return this.http.put(url, { country });

  }




  updatePhone(staffId: number, phone: string): Observable<any> {

    const url = `${this.baseUrl}/update/phone/${staffId}`;

    return this.http.put(url, { phone });

  }




  // getStaffByLastName(lastName: string): Observable<Staff[]> {

  //   const url = `${this.baseUrl}/lastname/${lastName}`;

  //   return this.http.get<Staff[]>(url);

  // }




  // getStaffByFirstName(firstName: string): Observable<Staff[]> {

  //   const url = `${this.baseUrl}/firstname/${firstName}`;

  //   return this.http.get<Staff[]>(url);

  // }




  // getStaffByEmail(email: string): Observable<Staff> {

  //   const url = `${this.baseUrl}/email/${email}`;

  //   return this.http.get<Staff>(url);

  // }




  // assignAddressToStaff(staffId: number, addressId: number): Observable<Staff> {

  //   const url = `${this.baseUrl}/${staffId}/address`;

  //   const body = { addressId: addressId };

  //   return this.http.put<Staff>(url, body);

  // }




  // getStaffByCity(city: string): Observable<Staff[]> {

  //   const url = `${this.baseUrl}/city/${city}`;

  //   return this.http.get<Staff[]>(url);

  // }




  // getStaffByCountry(country: string): Observable<Staff[]> {

  //   const url = `${this.baseUrl}/country/${country}`;

  //   return this.http.get<Staff[]>(url);

  // }




  // getStaffByPhone(phone: string): Observable<Staff[]> {

  //   const url = `${this.baseUrl}/phone/${phone}`;

  //   return this.http.get<Staff[]>(url);

  // }




  // updateStaffFirstName(staffId: number, firstName: string): Observable<Staff> {

  //   const url = `${this.baseUrl}/update/firstname/${staffId}`;

  //   const body = { firstName: firstName };

  //   return this.http.put<Staff>(url, body);

  // }




  // updateStaffLastName(staffId: number, lastName: string): Observable<Staff> {

  //   const url = `${this.baseUrl}/update/lastname/${staffId}`;

  //   const body = { lastName: lastName };

  //   return this.http.put<Staff>(url, body);

  // }




  // updateStaffEmail(staffId: number, email: string): Observable<Staff> {

  //   const url = `${this.baseUrl}/update/email/${staffId}`;

  //   const body = { email: email };

  //   return this.http.put<Staff>(url, body);

  // }




  // assignStoreToStaff(staffId: number, storeId: number): Observable<Staff> {

  //   const url = `${this.baseUrl}/update/store/${staffId}`;

  //   const body = { storeId: storeId };

  //   return this.http.put<Staff>(url, body);

  // }




  // updateStaffPhoneNumber(staffId: number, phone: string): Observable<Staff> {

  //   const url = `${this.baseUrl}/update/phone/${staffId}`;

  //   const body = { phone: phone };

  //   return this.http.put<Staff>(url, body);

  // }

}