import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServiceactorService } from '../serviceactor.service';
import { actor } from './actor';

 

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.css']
})
export class ActorComponent implements OnInit {
  actors: actor[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  allActorsList: actor[] = [];
  p: number = 1;

 

  constructor(private serviceactor: ServiceactorService,private route:ActivatedRoute) {}

 

  ngOnInit() {
    this.serviceactor.getAllActor().subscribe((data: any) => {
      this.actors = data;
      this.allActorsList = data;
    });
  }

 

  search(): void {
    if (this.searchQuery === '') {
      this.actors = this.allActorsList;
    } else {
     
        switch (this.searchAttribute) {
        
          case 'firstName':
            this.serviceactor
            .getbyactorfirstname(this.searchQuery)
            .subscribe((data: any) => (this.actors = [...data]));
          break;
           
          case 'lastName':
            this.serviceactor
            .getbyactorlastname(this.searchQuery)
            .subscribe((data: any) => (this.actors = [...data]));
            break;
         
            case 'findactorbyfilmid':
              this.serviceactor
              .getactorbyfilmbyfilmid(this.searchQuery)
              .subscribe((data: any) => (this.actors = [...data]));
            break;
          default:
            
        }
     
    }
  }
}


