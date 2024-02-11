import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { MessageResponse } from '../models/messageResponse.model';
import { UpdateProfileRequest } from '../models/updateProfileRequest.model';
import { SessionService } from './session.service';

/**
 * User service.
 */
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/user'

  /**
   * UserService constructor.
   * @param httpClient Http client.
   * @param sessionService Session service.
   */
  constructor(private httpClient: HttpClient,
              private sessionService: SessionService) { }

  /**
   * Get user profile.
   * @returns Observable.
   */
  public getProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}${this.pathService}/me`);
  }

  /**
   * Update user profile.
   * @param updateRequest Update request.
   * @returns Observable.
   */
  public updateProfile(updateRequest: UpdateProfileRequest): Observable<MessageResponse> {
    const id = this.sessionService.getSessionInformation()?.id;
    return this.httpClient.put<MessageResponse>(`${this.baseUrl}${this.pathService}/${id}`, updateRequest);
  }
}
