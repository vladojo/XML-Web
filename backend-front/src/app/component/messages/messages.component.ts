import { Component, OnInit } from '@angular/core';
import { User } from 'app/model/user';
import { Message } from 'app/model/message';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'app/service/user/user.service';
import { MessageService } from 'app/service/message.service';
import { AuthService } from 'app/service/user/auth.service';

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
    let res: User = JSON.parse(localStorage.getItem('user'));
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
         },
         err =>{
          this.popuniPodatke();
         }
        );
  }

  popuniPodatke(){
    let user:User = new User();
    user.firstName = "imenko";
    user.lastName = "prezimenko";
    user.email = "nekimail@gmail.com";

    let user2:User = new User();
    user2.firstName = "aaaa";
    user2.lastName = "bbbbb";
    user2.email = "nekimail@gmail.com";


    let message: Message = new Message();
    message.content = "ovo je neki nazovi sadrzaj";
    message.createdTime = "2019-12-12";

    let message2: Message = new Message();
    message2.content = "aaaaaaaaaaaa";
    message2.createdTime = "2019-12-12";

    let message3: Message = new Message();
    message3.content = "osgdvbfdwvfbd";
    message3.createdTime = "2019-12-12";

    let message4: Message = new Message();
    message4.content = "kraj";
    message4.createdTime = "2019-12-12";

    user.messages = [];
    user.messages.push(message);
    user.messages.push(message2);
    user.messages.push(message3);
    user.messages.push(message4);

    user2.messages = [];
    user2.messages.push(message);
    user2.messages.push(message4);

    this.inboxKorisnika = [];
    this.inboxKorisnika.push(user);
    this.inboxKorisnika.push(user2);
  }

  loggedUsersMessage(message: Message){
    if(message.receiver == this.loggedUser.id){
      return true;
    }
    return false;
  }

  sendMessage(){
    let d = new Date();
    let datestring = d.getFullYear() + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2) 
    //this.newMessage.createdTime = datestring;
    this.newMessage.sender = this.loggedUser.id;
    this.chat.forEach(element =>
      {
        if(element.receiver != this.loggedUser.id){
          this.newMessage.receiver = element.receiver;
        }
      })                          //agent, user, poruka
    this.chatService.sendMessage(this.newMessage.sender, this.newMessage.receiver, this.newMessage).subscribe(
      s => {
        this.chat.push(this.newMessage);
        this.newMessage = new Message();
      }
    )
  }

  changeActiveChat(chat:User){  //da promeni korisnika
    this.chat = chat.messages;
    this.newMessage.sender = chat.messages[0].sender; //proveri
    this.chatService.getMessages(this.loggedUser.id, chat.id).subscribe(
      s => {
        this.chat = s;
      }
    )
  }
}