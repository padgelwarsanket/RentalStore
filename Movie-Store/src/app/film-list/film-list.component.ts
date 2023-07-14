import { Component, OnInit } from '@angular/core';
import { Films } from './films';
import { FilmsService } from '../films.service';
import { Router } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css'],
})


export class FilmListComponent implements OnInit {
  films: any[] = [];
  allFilmlist: Films[] = [];
  filteredFilms: Films[] = [];
  title!: string;
  searchAttribute: string = '';
  searchQuery: string = '';
  p: number = 1;
  originalFilmlist: any[] = [];
  startYear: string = '';
  endYear: string = '';
  selectedAction: string = '';
  selectedFilm: any = {
    title: '',
    releaseYear: '',
    rentalDuration: '',
    rentalRate: '',
    rating: '',
    languageId: ''
    // Add other film properties
  };
  showTable: boolean = false;

  filmCountEntries: { key: string; value: string }[] = [];
  selectedFilms: any[] = [];
  


  constructor(private filmsService: FilmsService, private router: Router , private dataService: DataService) {}

  ngOnInit() {
    console.log(this.dataService.getUsername());
     this.dataService.setInventoryList([]);
     this.dataService.setRentalList([]);
    this.dataService.nullyfyIds();
    this.getFilms();
    {
    }
  }

  public getFilms() {
    this.filmsService.getAllFilmList().subscribe((data) => {
      this.allFilmlist = data;
      this.originalFilmlist = data;
      // this.films = data; // Assign the fetched films to the 'films' array
    });
  }

  //  search()  {
  //   if (this.title == "") {
  //      this.filmsService.getAllFilmList().subscribe(data => {
  //       this.allFilmlist = data;

  //     });; // Show all films if search query is empty

  //   } else{
  //     this.allFilmlist = this.allFilmlist.filter(data => {
  //       return data.title.toLocaleLowerCase().includes(this.title.toLocaleLowerCase());
  //     });
  //   }

  // }

  search(): void {
    // console.log(this.searchAttribute);

    if (this.searchQuery === '' &&  this.startYear === ' ' && this.endYear === ' ') {
      console.log("in true blog");
      
      this.allFilmlist = this.originalFilmlist;
    } else {
      console.log("in false blog");

      // this.allFilmlist = this.originalFilmlist.filter((film) => {
      switch (this.searchAttribute) {
        case 'title':
          this.filmsService
            .getbytitle(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'releaseYear':
          this.filmsService
            .getbyreleaseyear(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        // case 'filmId':
        //   return film.filmId === parseInt(this.searchQuery, 10);
        case 'rentalDurationgreaterthan':
          this.filmsService
            .rentalDurationgreaterthan(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'rentalDurationlowerthan':
          this.filmsService
            .rentalDurationlowerthan(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'rentalrategreaterthan':
          this.filmsService
            .rentalrategreaterthan(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));

          break;

        case 'rentalratelowerthan':
          this.filmsService
            .rentalratelowerthan(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'lengthgraeater':
          this.filmsService
            .lengthgraeater(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'lengthlower':
          this.filmsService
            .lengthlower(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'ratinggreaterthan':
          this.filmsService
            .ratinggreaterthan(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        case 'ratinglowerthan':
          this.filmsService
            .ratinglowerthan(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
        // case 'countbyyear':
        //   this.filmsService
        //     .countbyyear()
        //     .subscribe((data: any) => (this.allFilmlist = [...data]));
        //   break;
        case 'releaseYearRange':
          console.log('In relase');
          this.filmsService
            .releaseYearRange(this.startYear, this.endYear)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;

          case 'filmsbyactorid':
            this.filmsService
            .getfilmofactorbyactorid(this.searchQuery)
            .subscribe((data: any) => (this.allFilmlist = [...data]));
          break;
           


         
           
            // case 'buy':
            //   this.filteredFilms.forEach((film) => {
            //     film.action = 'buy';
            //   });
            //   break;

        //   case 'releaseYearRange':
        //         const [fromYear, toYear] = this.searchQuery.split('-').map(year => parseInt(year.trim(), 10));
        //         const filmReleaseYear = parseInt(film.releaseYear, 10);
        //         return filmReleaseYear >= fromYear && filmReleaseYear <= toYear;

        default:
          return undefined;
      }
      // });
    }
  }

  //   onBuyClick() {
  //     this.router.navigateByUrl('/payment');
  //   }

  onFieldChange(field: string, value: string): void {
    this.selectedFilm[field] = value;
  }

  updateFilm(field: string): void {
    // Call the API or perform the necessary logic to update the specific field of the film
    console.log(`Updating ${field} of film:`, this.selectedFilm[field]);
  }

  getFilmCountByYear() {
    this.filmsService.countFilmsByYear().subscribe(
      (countEntries: { [key: string]: number }) => {
        this.filmCountEntries = Object.entries(countEntries)
          .map(([year, count]) => ({ key: year.trim(), value: count.toString() }));
          this.showTable = true;
      },
      (error: any) => {
        console.error('Failed to retrieve film count by year', error);
        // Handle the error as needed
      }
    );
  }

  onFilmSelect(film: any) {
    const index = this.selectedFilms.indexOf(film);
    if (index === -1) {
      this.selectedFilms.push(film);
    } else {
      this.selectedFilms.splice(index, 1);
    }
    const title = film.title;
    const rentalRate = film.rentalRate;
    this.dataService.setSelectedIds(film.filmId)
    this.dataService.setSelectedFilmAndRate(title, rentalRate);
  }

  goToCart() {
    this.router.navigate(['/payment'], { state: { selectedFilms: this.selectedFilms } });
  }

}
