import { Component } from '@angular/core';
import { Films } from '../film-list/films';
import { FilmsService } from '../films.service';

@Component({
  selector: 'app-add-film',
  templateUrl: './add-film.component.html',
  styleUrls: ['./add-film.component.css'],
})
export class AddFilmComponent {
  film: Films = new Films();
  constructor(private filmsService: FilmsService) {}

  

  onAddFilm() {
    console.log(this.film);
    this.filmsService.addfilm(this.film).subscribe(() => {
      console.log('created');
    });
  }
}
