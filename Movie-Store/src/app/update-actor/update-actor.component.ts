import { Component } from '@angular/core';
import { ServiceactorService } from '../serviceactor.service';

@Component({
  selector: 'app-update-actor',
  templateUrl: './update-actor.component.html',
  styleUrls: ['./update-actor.component.css'],
})
export class UpdateActorComponent {
  actorId: number = 0;
  firstName: string = '';
  lastName: string = '';

  constructor(private serviceactor: ServiceactorService) {}

  updateActor() {
    console.log('in first name');
    console.log(this.actorId);
    console.log(this.firstName);

    if (this.firstName != "") {
      this.serviceactor
        .updateActorByFirstName(this.actorId, this.firstName)
        .subscribe(
          () => {
            console.log('First name updated successfully');
          },
          (error: any) => {
            console.error('Failed to update first name', error);
            alert('Failed to update first name');
          }
        );
    }

    if (this.lastName != "") {
      this.serviceactor
        .updateActorByLastName(this.actorId, this.lastName)
        .subscribe(
          () => {
            console.log('Last name updated successfully');
          },
          (error: any) => {
            console.error('Failed to update last name', error);
            alert('Failed to update last name');
          }
        );

      alert('Actor updated successfully');
    }
  }
}
