import { Component, OnDestroy, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../models/post.model';
import { BehaviorSubject, Subscription } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css'
})
export class DetailComponent implements OnInit, OnDestroy {

  private getPostSubscription!: Subscription;

  public onError = false;
  public post: Post | undefined;
  public postId!: number;

  constructor (private postService: PostService,
               private router: Router,
               private activatedRoute: ActivatedRoute) {}

  ngOnDestroy(): void {
    if (this.getPostSubscription != undefined) this.getPostSubscription.unsubscribe();
  }
  
  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id != null) {
      this.postId = +id;
      this.getPostSubscription = this.postService.getPost(+id).subscribe({
        next: (post: Post) => this.post = post,
        error: _ => this.onError = true
      });
    } else {
      this.onError = true;
    }
  }
}
