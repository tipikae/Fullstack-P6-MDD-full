import { Component, EventEmitter, Input, OnDestroy, Output, ViewChild } from '@angular/core';
import { Validators, FormBuilder, FormGroupDirective, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment.model';
import { SharedService } from 'src/app/shared/shared.service';
import { Subscription } from 'rxjs';

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

  constructor (private commentService: CommentService,
               private matSnackBar: MatSnackBar,
               private fb: FormBuilder,
               private sharedService: SharedService) {}

  ngOnDestroy(): void {
    if (this.addCommentSubscription != undefined) this.addCommentSubscription.unsubscribe();
  }
  
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

  public getErrorText(field: FormControl): string {
    return this.sharedService.getFormControlErrorText(field);
  }
}
