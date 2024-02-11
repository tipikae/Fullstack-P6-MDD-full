import { Component } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';

/**
 * Not found component.
 */
@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.css'
})
export class NotFoundComponent {

  /**
   * NotFound constructor.
   * @param sessionService Session service.
   */
  constructor (private sessionService: SessionService) {}

  /**
   * Check if current user is logged in.
   * @returns boolean
   */
  public isLogged(): boolean {
    return this.sessionService.isLoggedIn();
  }
}
