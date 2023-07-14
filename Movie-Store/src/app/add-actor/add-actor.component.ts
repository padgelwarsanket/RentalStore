import { Component } from '@angular/core';
import { ServiceactorService } from '../serviceactor.service';

import { actor } from '../actor/actor';


@Component({
  selector: 'app-add-actor',
  templateUrl: './add-actor.component.html',
  styleUrls: ['./add-actor.component.css'],
})
export class AddActorComponent {
  actors : actor = new actor();

  constructor(private serviceactor: ServiceactorService) {}

  onAddActor() {
    console.log(this.actors);
    this.serviceactor.addActor(this.actors).subscribe(() => {
      console.log('Actors created');
    });
  }
}
