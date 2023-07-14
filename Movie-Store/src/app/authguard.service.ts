import { Injectable } from '@angular/core';

import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';

import { Observable } from 'rxjs';

import { AuthService } from './auth.service';

import { StorageService } from './storage.service';




@Injectable({

  providedIn: 'root'

})





export class AuthGuard implements CanActivate {




  constructor(private authService: AuthService, private router: Router,private StorageService: StorageService) {}




  canActivate(




    route: ActivatedRouteSnapshot,




    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {




    if (this.StorageService.isAuthenticated()) {




      return true;




    } else {




      // Redirect to login page if not authenticated




      this.router.navigate(['/login']);




      return false;




    }




  }




}




// import { Injectable } from '@angular/core';

// import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

// import { Observable } from 'rxjs';

// import { StorageService } from './services/storage.service';





// @Injectable({

//   providedIn: 'root'

// })

// export class AuthGuard implements CanActivate {

//   constructor(private router: Router, private storageService: StorageService) {}




//   canActivate(

//     route: ActivatedRouteSnapshot,

//     state: RouterStateSnapshot

//   ): Observable<boolean> | Promise<boolean> | boolean {

//     if (this.storageService.isAuthenticated()) {

//       return true;

//     } else {

//       // Redirect to login page if not authenticated

//       this.router.navigate(['/login']);

//       return false;

//     }

//   }

// }