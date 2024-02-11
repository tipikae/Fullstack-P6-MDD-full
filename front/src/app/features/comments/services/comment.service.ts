import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageResponse } from 'src/app/models/messageResponse.model';
import { environment } from 'src/environments/environment';
import { Comment } from '../models/comment.model';

/**
 * Comment Service.
 */
@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/comment';

  /**
   * CommentService constructor.
   * @param httpclient 
   */
  constructor(private httpclient: HttpClient) { }

  /**
   * Get comments list.
   * @param postId Post id.
   * @returns Observable.
   */
  public getComments(postId: number): Observable<Comment[]> {
    return this.httpclient.get<Comment[]>(`${this.baseUrl}${this.pathService}/post/${postId}`);
  }

  /**
   * Post a new comment.
   * @param postId Post id.
   * @param comment Comment.
   * @returns Observable.
   */
  public addComment(postId: number, comment: Comment): Observable<MessageResponse> {
    return this.httpclient.post<MessageResponse>(`${this.baseUrl}${this.pathService}/post/${postId}`, comment);
  }
}
