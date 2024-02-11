import { Component, OnDestroy, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TopicService } from 'src/app/features/topics/services/topic.service';
import { BehaviorSubject, Subscription } from 'rxjs';
import { Topic } from 'src/app/features/topics/models/topic.model';
import { Post } from '../../models/post.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SharedService } from 'src/app/shared/shared.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit, OnDestroy {

  private getTopicsSubscription!: Subscription;
  private addPostSubscription!: Subscription;

  public onError = false;
  public topics$ = new BehaviorSubject<Topic[]>([]);

  public form = this.fb.group({
    topicId: [
      0,
      [
        Validators.required
      ]
    ],
    title: [
      '',
      [
        Validators.required,
        Validators.maxLength(50)
      ]
    ],
    content: [
      '',
      [
        Validators.required,
        Validators.maxLength(2000)
      ]
    ]
  });

  constructor (private postService: PostService,
               private topicService: TopicService,
               private fb: FormBuilder,
               private router: Router,
               private matSnackBar: MatSnackBar,
               private sharedService: SharedService) {}
  
  ngOnInit(): void {
    this.getTopicsSubscription = this.topicService.getTopics().subscribe({
      next: (topics: Topic[]) => this.topics$.next(topics),
      error: _ => this.onError = true
    });
  }

  ngOnDestroy(): void {
    if (this.getTopicsSubscription != undefined) this.getTopicsSubscription.unsubscribe();
    if (this.addPostSubscription != undefined) this.addPostSubscription.unsubscribe();
  }

  public submit(): void {
    const post = this.form.value as Post;
    this.addPostSubscription = this.postService.addPost(post).subscribe({
      next: _ => {
        this.matSnackBar.open('Article created successfully !', 'Close', { duration: 3000 });
        this.router.navigate(['posts']);
      },
      error: _ => this.onError = true
    });
  }

  public getErrorText(field: FormControl): string {
    return this.sharedService.getFormControlErrorText(field);
  }
}
