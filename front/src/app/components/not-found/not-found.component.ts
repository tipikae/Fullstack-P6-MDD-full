import { Component } from '@angular/core';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.css'
})
export class NotFoundComponent {

  constructor (private sessionService: SessionService) {}

  public isLogged(): boolean {
    return this.sessionService.isLoggedIn();
  }
}
