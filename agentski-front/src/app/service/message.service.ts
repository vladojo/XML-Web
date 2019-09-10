import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/user';
import { Message } from 'app/model/message';

@Injectable()
export class MessageService {

  constructor(private http: HttpClient) { }

  //preuzmi poruke sa kojima chetuje agent
  getInbox(userId: number): Observable<User[]>{
    return this.http.get<User[]>('/rest/agents/' + userId + '/chats');
  }

  //za svakog korisnika dovuci njegove poruke
  getMessages(agentId:number, userId:number): Observable<Message[]>{
    return this.http.get<Message[]>('/rest/agents/' + agentId + '/chats-with?user=' + userId);
  }

  //posalji poruku korisniku
  sendMessage(agentId: number, userId: number, message: Message): Observable<Message>{
    return this.http.post<Message>('/rest/agents/' + agentId + '/chats?user=' + userId, message);
  }
}
