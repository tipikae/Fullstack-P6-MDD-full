import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { SessionService } from 'src/app/services/session.service';
import { UserService } from 'src/app/services/user.service';
import { UpdateProfileRequest } from 'src/app/models/updateProfileRequest.model'
import { MatSnackBar } from '@angular/material/snack-bar';
import { Topic } from 'src/app/features/topics/models/topic.model';
import { SharedService } from 'src/app/shared/shared.service';
import { Subscription } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorResponse } from 'src/app/models/errorResponse.model';

/**
 * User profile component.
 */
@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrl: './me.component.css'
})
export class MeComponent implements OnInit, OnDestroy {

  private getProfileSubscription!: Subscription;
  private updateProfileSubscription!: Subscription;

  public onError: boolean = false;
  public error!: ErrorResponse;
  public onSuccess: boolean = false;
  public user: User | undefined;
  public topics: Topic[] = [];

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

  /**
   * MeComponent constructor.
   * @param userService User service.
   * @param sessionService  Session service.
   * @param fb Form builder.
   * @param router Router.
   * @param matSnackBar Material snack bar.
   * @param sharedService Shared service.
   */
  constructor (private userService: UserService,
               private sessionService: SessionService,
               private fb: FormBuilder,
               private router: Router,
               private matSnackBar: MatSnackBar,
               private sharedService: SharedService) {}

  /**
   * Init component.
   */
  ngOnInit(): void {
    this.getProfileSubscription = this.userService.getProfile().subscribe({
      next: (user: User) => {
        this.form.patchValue({
          username: user.username,
          email: user.email
        });
        this.topics = user.topics;
      }
    })
  }

  /**
   * Destroy component.
   */
  ngOnDestroy(): void {
    if (this.getProfileSubscription != undefined) this.getProfileSubscription.unsubscribe();
    if (this.updateProfileSubscription != undefined) this.updateProfileSubscription.unsubscribe();
  }

  /**
   * Submit update form.
   */
  public submit() :void {
    const updateRequest = this.form.value as UpdateProfileRequest;
    this.updateProfileSubscription = this.userService.updateProfile(updateRequest).subscribe({
      next: _ => {
        this.ngOnInit();
        this.matSnackBar.open('Profile updated !', 'Close', { duration: 3000 });
        this.onError = false;
      },
      error: (error: HttpErrorResponse) => {
        this.onError = true;
        this.error = error.error;
      }
    });
  }

  /**
   * Log out current user.
   */
  public logOut(): void {
    this.sessionService.logout();
    this.router.navigate(['']);
  }

  /**
   * Ge error message.
   * @param field Form field concerned.
   * @returns Error message.
   */
  public getErrorText(field: FormControl): string {
    return this.sharedService.getFormControlErrorText(field);
  }
}
