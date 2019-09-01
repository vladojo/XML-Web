import { User } from "./user";
import { Rating } from "./rating";
import { HousingUnit } from "./housingUnit";

export class Reservation{
    id: number;
    price: number;
	start: string; //"yyyy-MM-dd"
	end: string; //"yyyy-MM-dd"
    realised: boolean;
    user: User;
    rating: Rating;
    unit: HousingUnit;

    constructor(){
        this.id = 0;
        this.price = 0;
        this.start = "";
        this.end = "";
        this.realised = false;
        this.user = new User();
        this.rating = new Rating();
        this.unit = new HousingUnit();
    }
}