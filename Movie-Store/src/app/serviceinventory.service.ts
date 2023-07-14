import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventory, Inventoryone } from './inventory/inventory';

@Injectable({
  providedIn: 'root',
})
export class Serviceinventory {
  private apiUrl = 'http://localhost:9090/api/inventory';
  private posturl = 'http://localhost:9090/api/inventory/add';

  constructor(private http: HttpClient) { }


  addInventory(inventory: any): Observable<object> {

    return this.http.post(`${this.posturl}`, inventory);

  }
  getSingleInventory(val : number):Observable<number>{
    console.log("inside single inventory");
    return this.http.get<number>(`${this.apiUrl}/single/film/${val}`)

  }

  // addfilm(film:any):Observable<object>{
  //   console.log(film);
  //   return this.httpClient.post(`${this.posturl}`,film)
  // }

  getAllInventory(): Observable<Inventoryone[]> {
    return this.http.get<Inventoryone[]>(`${this.apiUrl}/films`);
  }
  getInventoryByStoreId(storeId: number): Observable<Inventory[]> {
    const url = `${this.apiUrl}/store/${storeId}`;
    return this.http.get<Inventory[]>(url);
  }

  getInventoryByFilmId(filmId: number): Observable<Inventory[]> {
    const url = `${this.apiUrl}/film/${filmId}`;
    return this.http.get<Inventory[]>(url);
  }

  getInventoryByFilmIdAndStoreId(filmId: number, storeId: number): Observable<Inventory[]> {
    const url = `${this.apiUrl}/film/${filmId}/store/${storeId}`;
    return this.http.get<Inventory[]>(url);
  }
}
