import { Component, EventEmitter, Input, OnDestroy, Output, ViewChild } from '@angular/core';
import { Validators, FormBuilder, FormGroupDirective, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment.model';
import { SharedService } from 'src/app/shared/shared.service';
import { Subscription } from 'rxjs';

/**
 * Comment form component.
 */
@Component({
  selector: 'app-comment-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnDestroy {

  private addCommentSubscription!: Subscription;

  public onError = false;
  public form = this.fb.group({
    comment: [
      '',
      [
        Validators.required,
        Validators.maxLength(255)
      ]
    ]
  });

  @Input() postId!: number;
  @Output() refreshList = new EventEmitter<boolean>();
  @ViewChild(FormGroupDirective) formGroupDirective!: FormGroupDirective;

  /**
   * Comment FormComponent constructor.
   * @param commentService Comment service.
   * @param matSnackBar MAterial snack bar.
   * @param fb Form builder.
   * @param sharedService Shared service.
   */
  constructor (private commentService: CommentService,
               private matSnackBar: MatSnackBar,
               private fb: FormBuilder,
               private sharedService: SharedService) {}

  /**
   * Call on destroy.
   */
  ngOnDestroy(): void {
    if (this.addCommentSubscription != undefined) this.addCommentSubscription.unsubscribe();
  }
  
  /**
   * Submit comment form.
   */
  public submit(): void {
    const comment = this.form.value as Comment;
    this.addCommentSubscription = this.commentService.addComment(this.postId, comment).subscribe({
      next: _ => {
        this.matSnackBar.open('Comment added successfully !', 'Close', { duration: 3000 });
        this.formGroupDirective.resetForm();
        this.refreshList.emit(true);
      },
      error: _ => this.onError = true
    });
  }

  /**
   * Get error message.
   * @param field Form field concerned.
   * @returns Error message.
   */
  public getErrorText(field: FormControl): string {
    return this.sharedService.getFormControlErrorText(field);
  }
}
