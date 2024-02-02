import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor (private sessionService: SessionService,
               private router: Router) {}

  public isLoggedIn(): boolean {
    return this.sessionService.isLoggedIn();
  }
}
