import { Address } from "./address";
import { UnitType } from "./unitType";
import { Option } from "./option";

export class HousingUnit{
    id: number;
    description: string;
    allowedPeople: number;
    daysForCancelling: number;
	allowedCancelling: boolean;
    price: number;
    rating: number;
    address: Address;
    type: UnitType;
    options: Option[];

    constructor(){
        this.id = 0;
        this.description = "";
        this.allowedPeople = undefined;
        this.allowedCancelling = false;
        this.daysForCancelling = undefined;
        this.price = undefined;
        this.rating= 0;
        this.address = new Address();
        this.type = new UnitType();
        this.options = [];
    }
}