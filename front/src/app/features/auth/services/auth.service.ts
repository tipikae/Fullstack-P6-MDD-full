import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../models/registerRequest.model';
import { Observable } from 'rxjs';
import { MessageResponse } from 'src/app/models/messageResponse.model';
import { LoginRequest } from '../models/loginRequest.model';
import { SessionInformation } from 'src/app/models/sessionInformation.model';
import { environment } from 'src/environments/environment';

/**
 * Auth service.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/auth';

  /**
   * AuthService constructor.
   * @param httpClient Http client.
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Post a register request.
   * @param registerRequest Register request.
   * @returns Observable
   */
  public register(registerRequest: RegisterRequest): Observable<MessageResponse> {
    return this.httpClient.post<MessageResponse>(`${this.baseUrl}${this.pathService}/register`, registerRequest);
  }

  /**
   * Post a login request.
   * @param loginRequest Login request.
   * @returns Observable.
   */
  public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.baseUrl}${this.pathService}/login`, loginRequest);
  }
}
