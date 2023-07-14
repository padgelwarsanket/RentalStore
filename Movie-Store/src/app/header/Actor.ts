export class Actor {
    id: number;
    firstName: string;
    filmCount: number;
  
    constructor(id: number, firstName: string, filmCount: number) {
      this.id = id;
      this.firstName = firstName;
      this.filmCount = filmCount;
    }
  }