import { Injectable } from '@angular/core';
import { SessionInformation } from '../models/sessionInformation.model';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public login(sessionInformation: SessionInformation): void {
    const expiresAt = moment().add(86400, 'seconds');
    localStorage.setItem('mdd_session', JSON.stringify(sessionInformation));
    localStorage.setItem('mdd_expiresAt', JSON.stringify(expiresAt.valueOf()));
  }

  public logout(): void {
    localStorage.removeItem('mdd_session');
    localStorage.removeItem('mdd_expiresAt');
  }

  public getSessionInformation(): SessionInformation | null {
    const sessionInformation = localStorage.getItem('mdd_session');
    if (sessionInformation != null) {
      return JSON.parse(sessionInformation) as SessionInformation;
    }
    return null;
  }

  public isLoggedIn(): boolean {
    const expiration = this.getExpiration();
    if (expiration != null) {
      return moment().isBefore(expiration);
    }
    return false;
  }

  private getExpiration(): moment.Moment | null {
    const expiration = localStorage.getItem('mdd_expiresAt');
    if (expiration != null) {
      return moment(JSON.parse(expiration));
    }
    return null;
  }
}
