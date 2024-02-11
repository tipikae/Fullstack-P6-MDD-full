import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

/**
 * Header component.
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  /**
   * HeaderComponent constructor.
   * @param sessionService Session service.
   * @param router Router.
   */
  constructor (private sessionService: SessionService,
               private router: Router) {}

  /**
   * Check if current user is logged in.
   * @returns boolean
   */             
  public isLoggedIn(): boolean {
    return this.sessionService.isLoggedIn();
  }
}
