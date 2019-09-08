import { User } from "./user";

export class Message{
    id: number; 
	createdTime: string; //"yyyy-MM-dd@HH:mm:ss"
    content: string;
    sender: number;
    receiver: number;
	
    constructor(){
        this.id = 0;
        var d = new Date();
        this.createdTime = ("0" + d.getDate()).slice(-2) + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" +
        d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2) + ":" + "00";
        this.content = "";
        this.sender = 0;
        this.receiver = 0;
    }
}