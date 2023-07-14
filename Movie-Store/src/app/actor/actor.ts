export class actor{
    static actorId(actorId: any) {
      throw new Error('Method not implemented.');
    }
    id:number ;
    firstName:string; 
    lastName:string ;
    lastUpdate:any;

 
    constructor(){
        this.id=0;
        this.lastName="";
        this.firstName="";
        this.lastUpdate="";
    }
}