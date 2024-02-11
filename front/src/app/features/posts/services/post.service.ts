import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from '../models/post.model';
import { MessageResponse } from 'src/app/models/messageResponse.model';

/**
 * Post service.
 */
@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/post';

  /**
   * PostService constructor.
   * @param httpClient Http client.
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Get posts list.
   * @param order Posts list order.
   * @returns Observable.
   */
  public getPosts(order: string): Observable<Post[]> {
    let queryParams = new HttpParams().append('order', order);
    return this.httpClient.get<Post[]>(`${this.baseUrl}${this.pathService}`, { params: queryParams });
  }

  /**
   * Post a new post.
   * @param post Post to create.
   * @returns Observable.
   */
  public addPost(post: Post): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.baseUrl}${this.pathService}`, post);
  }

  /**
   * Get a post by id.
   * @param id Post id.
   * @returns Observable.
   */
  public getPost(id: number): Observable<Post> {
    return this.httpClient.get<Post>(`${this.baseUrl}${this.pathService}/${id}`);
  }
}
