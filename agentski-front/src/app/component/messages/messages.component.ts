import { Component, OnInit } from '@angular/core';
import { User } from 'app/model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { UserService } from 'app/service/user/user.service';
import { Message } from 'app/model/message';
import { MessageService } from 'app/service/message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  inboxKorisnika: User[] = [];
  chat: Message[] = [];
  private sub: any;
  loggedUser: User = new User();
  newMessage: Message = new Message();
  p:any;

  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router, 
    private authService: AuthService, private chatService: MessageService) {
    let res: User = JSON.parse(localStorage.getItem('korisnik'));
    if(res != null){
       this.loggedUser = res; //this.authService.getUsername(res);
      // //this.chat.messages = [];
    }
   }

  ngOnInit() {
        this.chatService.getInbox(this.loggedUser.id).subscribe(
         s => {
           this.inboxKorisnika = s;
           this.inboxKorisnika.forEach(element => {
             this.chatService.getMessages(this.loggedUser.id, element.id).subscribe( //dobavi sve poruke za tog korisnika
               ss => {
                 element.messages = ss;
               }
             )
           })
         }
         )
  }

  loggedUsersMessage(message: Message){
    if(message.receiver.id == this.loggedUser.id){
      return true;
    }
    return false;
  }

  sendMessage(){
    let d = new Date();
    let datestring = d.getFullYear() + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2) 
    //this.newMessage.createdTime = datestring;
    this.newMessage.sender.id = this.loggedUser.id;
    this.chat.forEach(element =>
      {
        if(element.receiver.id != this.loggedUser.id){
          this.newMessage.receiver = element.receiver;
        }
      })                          //agent, user, poruka
    this.chatService.sendMessage(this.newMessage.sender.id, this.newMessage.receiver.id, this.newMessage).subscribe(
      s => {
        this.chat.push(this.newMessage);
        this.newMessage = new Message();
      }
    )
  }

  changeActiveChat(chat:User){  //da promeni korisnika
    this.chat = chat.messages;
    this.newMessage.receiver = chat.messages[0].sender;
    this.chatService.getMessages(this.loggedUser.id, chat.id).subscribe(
      s => {
        this.chat = s;
      }
    )
  }
}
