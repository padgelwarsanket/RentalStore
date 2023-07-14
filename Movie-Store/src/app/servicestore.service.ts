import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Store } from './storelist/storefilm';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class servicestore {
  private baseUrl = 'http://localhost:9090/api/store';

  constructor(private http: HttpClient) {}

  getAllStores(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  getStoreByCity(city: string): Observable<Store[]> {
    const url = `${this.baseUrl}/city/${city}`;

    return this.http.get<Store[]>(url);
  }

  getStoreByCountry(country: string): Observable<Store[]> {
    const url = `${this.baseUrl}/country/${country}`;

    return this.http.get<Store[]>(url);
  }

  getStoreByphone(phone: any): Observable<Store[]> {
    const url = `${this.baseUrl}/phone/${phone}`;

    return this.http.get<Store[]>(url);
  }

  searchstorelist(searchAttribute: string, searchQuery: string) {
    console.log('Entered');
    const url = `${this.baseUrl}/store`;
    return this.http.get(url);
  }

  addStore(storeDto: Store): Observable<Store> {
    return this.http.post<Store>(this.baseUrl, storeDto);
  }

  // updateStoreByAddress(storeId: number, addressId: number): Observable<Store> {
  //   const url = `${this.baseUrl}/${storeId}/address`;
  //   const body = addressId;
  //   return this.http.put<Store>(url, body);
  // }

  // getStoreByCity(city: string): Observable<Store[]> {
  //   const url = `${this.baseUrl}/city/${city}`;
  //   return this.http.get<Store[]>(url);
  // }

  // getStoreByLastName(lastName: string): Observable<Store[]> {
  //   const url = `${this.baseUrl}/lastname/${lastName}`;
  //   return this.http.get<Store[]>(url);
  // }

  // getStoreByFirstName(firstName: string): Observable<Store[]> {
  //   const url = `${this.baseUrl}/firstname/${firstName}`;
  //   return this.http.get<Store[]>(url);
  // }

  // getStaffByEmail(email: string): Observable<Store> {
  //   const url = `${this.baseUrl}/email/${email}`;
  //   return this.http.get<Store>(url);
  // }

  // assignAddressToStaff(
  //   staffId: number,
  //   addressId: number
  // ): Observable<Store> {
  //   const url = `${this.baseUrl}/${staffId}/address`;
  //   const body = { addressId: addressId };
  //   return this.http.put<Store>(url, body);
  // }

  // getStaffByCountry(country: string): Observable<Store[]> {
  //   const url = `${this.baseUrl}/country/${country}`;
  //   return this.http.get<Store[]>(url);
  // }

  // getStaffByPhone(phone: string): Observable<Store[]> {
  //   const url = `${this.baseUrl}/phone/${phone}`;
  //   return this.http.get<Store[]>(url);
  // }

  // updateStaffFirstName(
  //   staffId: number,
  //   firstName: string
  // ): Observable<Store> {
  //   const url = `${this.baseUrl}/update/firstname/${staffId}`;
  //   const body = { firstName: firstName };
  //   return this.http.put<Store>(url, body);
  // }

  // updateStaffLastName(staffId: number, lastName: string): Observable<Store> {
  //   const url = `${this.baseUrl}/update/lastname/${staffId}`;
  //   const body = { lastName: lastName };
  //   return this.http.put<Store>(url, body);
  // }

  // updateStaffEmail(staffId: number, email: string): Observable<Store> {
  //   const url = `${this.baseUrl}/update/email/${staffId}`;
  //   const body = { email: email };
  //   return this.http.put<Store>(url, body);
  // }

  // assignStoreToStaff(staffId: number, storeId: number): Observable<Store> {
  //   const url = `${this.baseUrl}/update/store/${staffId}`;
  //   const body = { storeId: storeId };
  //   return this.http.put<Store>(url, body);
  // }

  // updateStaffPhoneNumber(
  //   staffId: number,
  //   phone: string
  // ): Observable<Store> {
  //   const url = `${this.baseUrl}/update/phone/${staffId}`;
  //   const body = { phone: phone };
  //   return this.http.put<Store>(url, body);
  // }
}
