import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Validators, FormBuilder, FormGroupDirective, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment.model';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-comment-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {

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
  
  public submit(): void {
    const comment = this.form.value as Comment;
    this.commentService.addComment(this.postId, comment).subscribe({
      next: _ => {
        this.matSnackBar.open('Comment added successfully !', 'Close', { duration: 3000 });
        this.formGroupDirective.resetForm();
        this.refreshList.emit(true);
      }
    });
  }

  public getErrorText(field: FormControl): string {
    return this.sharedService.getFormControlErrorText(field);
  }
}
