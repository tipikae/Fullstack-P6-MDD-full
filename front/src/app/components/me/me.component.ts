import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { SessionService } from 'src/app/services/session.service';
import { UserService } from 'src/app/services/user.service';
import { UpdateProfileRequest } from 'src/app/models/updateProfileRequest.model'
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrl: './me.component.css'
})
export class MeComponent implements OnInit {

  public onError: boolean = false;
  public onSuccess: boolean = false;
  public user: User | undefined;

  public form = this.fb.group({
    username: [
      '', 
      [
        Validators.required,
        Validators.maxLength(50)
      ],
    ],
    email: [
      '',
      [
        Validators.required,
        Validators.maxLength(255),
        Validators.email
      ]
    ]
  });

  constructor (private userService: UserService,
               private sessionService: SessionService,
               private fb: FormBuilder,
               private router: Router,
               private matSnackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.userService.getProfile().subscribe({
      next: (user: User) => this.form.patchValue({
        username: user.username,
        email: user.email
      })
    })
  }

  public submit() :void {
    const updateRequest = this.form.value as UpdateProfileRequest;
    this.userService.updateProfile(updateRequest).subscribe({
      next: _ => {
        this.ngOnInit();
        this.matSnackBar.open('Profile updated !', 'Close', { duration: 3000 });
      },
      error: _ => this.onError = true
    });
  }

  public logOut(): void {
    this.sessionService.logout();
    this.router.navigate(['']);
  }
}
