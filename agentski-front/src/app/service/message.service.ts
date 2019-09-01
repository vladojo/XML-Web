import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/user';
import { Message } from 'app/model/message';

@Injectable()
export class MessageService {

  constructor(private http: HttpClient) { }

  //preuzmi poruke sa kojima chetuje agent
  //rest/agents/{id}/chats
  getInbox(userId: number): Observable<User[]>{
    //userId = 1;
    return this.http.get<User[]>('/rest/agents/' + userId + '/chats');
  }

  //rest/agents/{id}/chats-with
  //za svakog korisnika dovuci njegove poruke
  getMessages(agentId:number, userId:number): Observable<Message[]>{
    //agentId = 1; 
    //userId = 3;
    return this.http.get<Message[]>('/rest/agents/' + agentId + '/chats-with?user=' + userId);
  }

  //posalji poruku korisniku
  sendMessage(agentId: number, userId: number, message: Message): Observable<Message>{
    //agentId = 3;
    return this.http.post<Message>('/rest/agents/' + agentId + '/chats?user=' + userId, message);
  }
}
