import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from '../models/post.model';
import { MessageResponse } from 'src/app/models/messageResponse.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/post';

  constructor(private httpClient: HttpClient) { }

  public getPosts(order: string): Observable<Post[]> {
    let queryParams = new HttpParams().append('order', order);
    return this.httpClient.get<Post[]>(`${this.baseUrl}${this.pathService}`, { params: queryParams });
  }

  public addPost(post: Post): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.baseUrl}${this.pathService}`, post);
  }

  public getPost(id: number): Observable<Post> {
    return this.httpClient.get<Post>(`${this.baseUrl}${this.pathService}/${id}`);
  }
}
