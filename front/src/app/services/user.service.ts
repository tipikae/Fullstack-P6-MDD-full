import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = environment.baseUrl;
  private pathService: string = '/user'

  constructor(private httpClient: HttpClient) { }

  public getProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}${this.pathService}/me`);
  }
}
