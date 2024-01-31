import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';
import { MessageResponse } from '../models/messageResponse.model';
import { UpdateProfileRequest } from '../models/updateProfileRequest.model';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/user'

  constructor(private httpClient: HttpClient,
              private sessionService: SessionService) { }

  public getProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}${this.pathService}/me`);
  }

  public updateProfile(updateRequest: UpdateProfileRequest): Observable<MessageResponse> {
    const id = this.sessionService.getSessionInformation()?.id;
    return this.httpClient.put<MessageResponse>(`${this.baseUrl}${this.pathService}/${id}`, updateRequest);
  }
}
