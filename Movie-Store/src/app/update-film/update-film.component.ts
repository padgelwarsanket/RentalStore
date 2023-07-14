import { HttpHeaders } from '@angular/common/http';

import { Component, OnInit } from '@angular/core';

import { Films } from '../film-list/films';

import { FilmsService } from '../films.service';

@Component({
  selector: 'app-update-film',

  templateUrl: './update-film.component.html',

  styleUrls: ['./update-film.component.css'],
})
export class UpdateFilmComponent implements OnInit {
  filmId!: number;

  title: string = '';

  releaseYear: string = '';

  rentalDuration: number = 0;

  rentalRate: number = 0;

  rating: string = '';

  // language:any={

  //   languageId:0

  // }

  languageId!: number;

  allFilmlist: Films[] = [];

  constructor(private filmsService: FilmsService) {}

  ngOnInit(): void {}

  updateFilm() {
    if (this.filmId) {
      if (this.title) {
        this.filmsService.updateFilmByTitle(this.filmId, this.title).subscribe(
          () => {
            console.log('Title updated successfully');
          },

          (error) => {
            console.error('Failed to update title', error);

            alert('Failed to update title');
          }
        );
      }

      if (this.releaseYear) {
        this.filmsService

          .updateFilmByReleaseYear(this.filmId, this.releaseYear)

          .subscribe(
            (response) => {
              console.log('Release Year updated successfully');
            },

            (error) => {
              console.error('Failed to update release year', error);

              alert('Failed to update release year');
            }
          );
      }

      if (this.rentalDuration) {
        this.filmsService

          .updateFilmByRentalDuration(this.filmId, this.rentalDuration)

          .subscribe(
            (response) => {
              console.log('Rental duration updated successfully');
            },

            (error: any) => {
              console.error('Failed to update rental duration', error);

              alert('Failed to update rental duration');
            }
          );
      }

      if (this.rentalRate) {
        this.filmsService

          .updateFilmByRentalRate(this.filmId, this.rentalRate)

          .subscribe(
            (response) => {
              console.log('Rental rate updated successfully');
            },

            (error: any) => {
              console.error('Failed to update rental rate', error);

              alert('Failed to update rental rate');
            }
          );
      }

      if (this.rating) {
        this.filmsService

          .updateFilmByRating(this.filmId, this.rating)

          .subscribe(
            (response) => {
              console.log('Rating updated successfully');
            },

            (error: any) => {
              console.error('Failed to update rating', error);

              alert('Failed to update rating');
            }
          );
      }

      if (this.languageId) {
        this.filmsService

          .updateFilmByLanguage(this.filmId, this.languageId)

          .subscribe(
            (response) => {
              console.log('Language updated successfully');
            },

            (error: any) => {
              console.error('Failed to update language', error);

              alert('Failed to update language');
            }
          );
      }

      alert('Film updated successfully');
    } else {
      alert('Please provide a valid Film ID');
    }
  }

  public getFilms() {
    this.filmsService.getAllFilmList().subscribe((data) => {
      this.allFilmlist = data;

      // this.films = data; // Assign the fetched films to the 'films' array
    });
  }
}
