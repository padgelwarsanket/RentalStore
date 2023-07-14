import { Component, OnInit } from '@angular/core';
import { actor } from '../actor/actor';
import { FilmsService } from '../films.service';
import { ServiceactorService } from '../serviceactor.service';
import { Actor } from './Actor';
import { Films } from '../film-list/films';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  topActors: Actor[] = [];
  

  constructor(private serviceactor: ServiceactorService, private filmservice: FilmsService ) {}

  ngOnInit() {
    this.getTopActorByFilmCount();
  }

  getTopActorByFilmCount() {
    this.serviceactor.getTopActors().subscribe(
      (actors: Actor[]) => {
        this.topActors = actors;
      },
      (error: any) => {
        console.error(error);
      }
    );

   

    
    
      
  }
}
