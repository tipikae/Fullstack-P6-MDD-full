import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../models/registerRequest.model';
import { Observable } from 'rxjs';
import { MessageResponse } from 'src/app/models/messageResponse.model';
import { LoginRequest } from '../models/loginRequest.model';
import { SessionInformation } from 'src/app/models/sessionInformation.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/auth';

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: RegisterRequest): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.baseUrl}${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.baseUrl}${this.pathService}/login`, loginRequest);
  }
}
