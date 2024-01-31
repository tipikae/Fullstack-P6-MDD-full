import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Topic } from '../models/topic.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { MessageResponse } from 'src/app/models/messageResponse.model';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/topic';

  constructor(private httpClient: HttpClient) { }

  public getTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.baseUrl}${this.pathService}`);
  }

  public subscribe(id: number): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.baseUrl}${this.pathService}/${id}/subscribe`, '');
  }

  public unsubscribe(id: number): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${this.baseUrl}${this.pathService}/${id}/subscribe`); 
  }
}
