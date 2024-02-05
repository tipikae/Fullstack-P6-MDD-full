import { Component, OnDestroy, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { BehaviorSubject, Subscription } from 'rxjs';
import { Post } from '../../models/post.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit, OnDestroy {

  private getPostsSubscription!: Subscription;

  public posts$: BehaviorSubject<Post[]> = new BehaviorSubject<Post[]>([]);
  public onError: boolean = false;
  public order: string = "desc";

  constructor (private postService: PostService,
               private router: Router) {}

  ngOnDestroy(): void {
    if (this.getPostsSubscription != undefined) this.getPostsSubscription.unsubscribe();
  }
  
  ngOnInit(): void {
    this.getPostsSubscription = this.postService.getPosts(this.order).subscribe({
      next: (posts: Post[]) => this.posts$.next(posts)
    });
  }

  public sortList(order: string): void {
    this.order = order;
    this.ngOnInit();
  }
}
