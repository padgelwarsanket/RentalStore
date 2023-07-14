import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule } from '@angular/material/icon';
import { PaymentListComponent } from './payment-list/payment-list.component';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddInventoryComponent } from './add-inventory/add-inventory.component';
import { NgxFileDropModule } from 'ngx-file-drop';
import { HeaderComponent } from './header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SidebarComponent } from './sidebar/sidebar.component';
import { LoginComponent } from './login/login.component';
// import { AddFilmComponent } from './add-film/add-film.component';
import { AddStaffComponent } from './add-staff/add-staff.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { AddStoreComponent } from './add-store/add-store.component';
import { PaymentEntryComponent } from './payment-entry/payment-entry.component';
import { HttpClientModule } from '@angular/common/http';
import { ViewPaymentComponent } from './view-payment/view-payment.component';
import { HomePageComponent } from './home-page/home-page.component';
import { SlideHomeComponent } from './slide-home/slide-home.component';
import { ProfileComponent } from './profile/profile.component';
import { FilmListComponent } from './film-list/film-list.component';
import { AddFilmComponent } from './add-film/add-film.component';
import { ActorComponent } from './actor/actor.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddActorComponent } from './add-actor/add-actor.component';
import { UpdateFilmComponent } from './update-film/update-film.component';
import { CartComponent } from './cart/cart.component';
import { UpdateActorComponent } from './update-actor/update-actor.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { PaymentComponent } from './payment/payment.component';
import { RentalComponent } from './rental/rental.component';
import { RentreceiptComponent } from './rentreceipt/rentreceipt.component';
import { StaffComponent } from './staff/staff.component';
import { StoreListComponent } from './storelist/storefilm.component';
import { InventoryComponent } from './inventory/inventory.component';



import { FormsModule } from '@angular/forms';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { UpdateStaffComponent } from './update-staff/update-staff.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    LoginComponent,
    // AddFilmComponent,
    AddStaffComponent,
    AddCustomerComponent,
    AddStoreComponent,
    PaymentEntryComponent,
    ViewPaymentComponent,
    HomePageComponent,
    SlideHomeComponent,
    ProfileComponent,
    FilmListComponent,
    AddFilmComponent,
    ActorComponent,
    FooterComponent,
    NavbarComponent,
    AddActorComponent,
    UpdateFilmComponent,
    CartComponent,
    UpdateActorComponent,
    StaffComponent,
   AddInventoryComponent,
    CustomerListComponent,
    PaymentComponent,
    RentalComponent,
    RentreceiptComponent,
    UpdateCustomerComponent,
    InventoryComponent,
    StoreListComponent,
    UpdateStaffComponent,
    PaymentListComponent
    
    
    
 
    
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    NgxFileDropModule,
    MatIconModule,
    MatToolbarModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
