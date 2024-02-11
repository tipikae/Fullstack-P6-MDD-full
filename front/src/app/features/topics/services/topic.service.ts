import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Topic } from '../models/topic.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { MessageResponse } from 'src/app/models/messageResponse.model';

/**
 * Topic service.
 */
@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/topic';

  /**
   * TopicService constructor.
   * @param httpClient Http client.
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Get topics list.
   * @returns Observable.
   */
  public getTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.baseUrl}${this.pathService}`);
  }

  /**
   * Post a topic subscription.
   * @param id Topic id.
   * @returns Observable.
   */
  public subscribe(id: number): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.baseUrl}${this.pathService}/${id}/subscribe`, '');
  }

  /**
   * Delete a topic subscription.
   * @param id Topic id.
   * @returns Observable.
   */
  public unsubscribe(id: number): Observable<MessageResponse> {
    return this.httpClient.delete<MessageResponse>(`${this.baseUrl}${this.pathService}/${id}/subscribe`); 
  }
}
