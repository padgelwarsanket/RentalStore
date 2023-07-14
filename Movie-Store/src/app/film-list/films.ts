export class Films {
    filmId : number ;
    title : string;
    description : string;
    releaseYear: string;
    languageId :any;
    orignalLanguageId  : any;
    rentalDuration : number;
    rentalRate : number;
    length : number;
    replacementCost : number;
    rating : string;
    specialFeatures : string;
    lastUpdate : any;
  

    constructor(){
        this.filmId = 0;
    this.title = " ";
    this.description = "";
    this.releaseYear = " ";
    this.languageId = "";
    this.orignalLanguageId = "";
    this.rentalDuration = 0;
    this.rentalRate = 0;
    this.length = 0;
    this.replacementCost = 0;
    this.rating = "";
    this.specialFeatures = "";
    this.lastUpdate = "";
    }

    
    
    
}
