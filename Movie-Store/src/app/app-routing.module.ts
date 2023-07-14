import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';

import { ActorComponent } from './actor/actor.component';

import { AddActorComponent } from './add-actor/add-actor.component';

import { AddCustomerComponent } from './add-customer/add-customer.component';

import { AddFilmComponent } from './add-film/add-film.component';
import { AddInventoryComponent } from './add-inventory/add-inventory.component';

import { AddStaffComponent } from './add-staff/add-staff.component';

import { AuthGuard } from './authguard.service';




import { CartComponent } from './cart/cart.component';

import { CustomerListComponent } from './customer-list/customer-list.component';

import { FilmListComponent } from './film-list/film-list.component';

import { HeaderComponent } from './header/header.component';

import { HomePageComponent } from './home-page/home-page.component';
import { InventoryComponent } from './inventory/inventory.component';

import { LoginComponent } from './login/login.component';

import { PaymentEntryComponent } from './payment-entry/payment-entry.component';
import { PaymentListComponent } from './payment-list/payment-list.component';

import { PaymentComponent } from './payment/payment.component';

import { ProfileComponent } from './profile/profile.component';

import { RentalComponent } from './rental/rental.component';

import { RentreceiptComponent } from './rentreceipt/rentreceipt.component';

import { SidebarComponent } from './sidebar/sidebar.component';

import { SlideHomeComponent } from './slide-home/slide-home.component';

import { StaffComponent } from './staff/staff.component';
import { StoreListComponent } from './storelist/storefilm.component';

import { UpdateActorComponent } from './update-actor/update-actor.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';

import { UpdateFilmComponent } from './update-film/update-film.component';
import { UpdateStaffComponent } from './update-staff/update-staff.component';


import { ViewPaymentComponent } from './view-payment/view-payment.component';






const routes: Routes = [

  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'home-page', component: HomePageComponent, canActivate: [AuthGuard] },

  { path: 'header', component: HeaderComponent, canActivate: [AuthGuard] },

  { path: 'payment', component: PaymentComponent, canActivate: [AuthGuard] },




  { path: 'login', component: LoginComponent },

  { path: 'sidebar', component: SidebarComponent, canActivate: [AuthGuard] },

  { path: 'add-staff', component: AddStaffComponent, canActivate: [AuthGuard] },

  { path: 'add-customer', component: AddCustomerComponent, canActivate: [AuthGuard] },

  { path: 'payment-entry', component: PaymentEntryComponent, canActivate: [AuthGuard] },

  { path: 'view-revenue', component: ViewPaymentComponent, canActivate: [AuthGuard] },

  // { path: 'home-page', component: HomePageComponent},

  { path: 'slide', component: SlideHomeComponent, canActivate: [AuthGuard] },

  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },

  { path: 'film-list', component: FilmListComponent, canActivate: [AuthGuard] },

  { path: 'actor', component: ActorComponent, canActivate: [AuthGuard] },

  { path: 'add-film', component: AddFilmComponent, canActivate: [AuthGuard] },

  { path: 'update-film', component: UpdateFilmComponent, canActivate: [AuthGuard] },

  { path: 'cart', component: CartComponent, canActivate: [AuthGuard] },

  { path: 'staff', component: StaffComponent, canActivate: [AuthGuard] },

  { path: 'update-actor', component: UpdateActorComponent, canActivate: [AuthGuard] },

  { path: 'add-actor', component: AddActorComponent, canActivate: [AuthGuard] },

  { path: 'customer-list', component: CustomerListComponent, canActivate: [AuthGuard] },

  { path: 'rental', component: RentalComponent, canActivate: [AuthGuard] },

  { path: 'rentreceipt', component: RentreceiptComponent, canActivate: [AuthGuard] },
  { path: 'update-customer', component: UpdateCustomerComponent, canActivate: [AuthGuard] },
  {path:'update-staff', component: UpdateStaffComponent},
  {path:'payment-list', component: PaymentListComponent},
  {path:'inventory', component: InventoryComponent},
  {path:'add-inventory', component: AddInventoryComponent},
  {path:'storelist' , component:StoreListComponent}


  

];




@NgModule({

  imports: [RouterModule.forRoot(routes)],

  exports: [RouterModule]

})

export class AppRoutingModule { }