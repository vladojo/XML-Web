import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/user';
import { Message } from 'app/model/message';
import { environment } from 'environments/environment.prod';

@Injectable()
export class MessageService {

  baseUrl: string = environment.baseUrl + '/poruka';

  constructor(private http: HttpClient) { }

  //preuzmi poruke sa kojima chetuje agent
  getInbox(userId: number): Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl + '/rest/users/' + userId + '/chats');
  }

  //za svakog korisnika dovuci njegove poruke
  getMessages(agentId:number, userId:number): Observable<Message[]>{
    return this.http.get<Message[]>(this.baseUrl + '/rest/users/' + agentId + '/chats-with?agent=' + userId);
  }

  //posalji poruku korisniku
  sendMessage(agentId: number, userId: number, message: Message): Observable<Message>{
    return this.http.post<Message>(this.baseUrl + '/rest/users/' + agentId + '/chats?agent=' + userId, message);
  }
}

