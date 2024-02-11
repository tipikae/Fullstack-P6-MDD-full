import { Component } from '@angular/core';
import { SessionService } from './services/session.service';

/**
 * App component.
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  /**
   * AppComponent constructor.
   * @param sessionService Session service.
   */
  constructor (private sessionService: SessionService) {}

  /**
   * Check if current user is logged in.
   * @returns true if current user is logged in, false otherwise.
   */
  public isLogged(): boolean {
    return this.sessionService.isLoggedIn();
  }
 }
