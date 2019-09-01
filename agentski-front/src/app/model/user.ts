import { Address } from "./address";
import { Message } from "./message";

export class User{
    id: number;
	firstName: string;
	lastName: string;
	email: string;
	password: string;
	state: string;
	type: string;
	workCertificateNumber: string;
    address: Address;
    messages: Message[];
    
    constructor(){
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.state = "ACTIVE";
        this.type = "USER";
        this.workCertificateNumber = "";
        this.address = new Address();
        this.messages = [];
    }
}