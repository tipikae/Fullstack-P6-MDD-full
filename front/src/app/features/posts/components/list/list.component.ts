import { Component, OnDestroy, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { BehaviorSubject, Subscription } from 'rxjs';
import { Post } from '../../models/post.model';
import { Router } from '@angular/router';

/**
 * Posts list component.
 */
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

  /**
   * ListComponent constructor.
   * @param postService Post service.
   * @param router Router.
   */
  constructor (private postService: PostService,
               private router: Router) {}
  
  /**
   * Init component.
   */
  ngOnInit(): void {
    this.getPostsSubscription = this.postService.getPosts(this.order).subscribe({
      next: (posts: Post[]) => this.posts$.next(posts)
    });
  }

  /** 
   * Call on destroy.
   */
  ngOnDestroy(): void {
    if (this.getPostsSubscription != undefined) this.getPostsSubscription.unsubscribe();
  }

  /**
   * Sort posts list.
   * @param order List order ('asc' | 'desc').
   */
  public sortList(order: string): void {
    this.order = order;
    this.ngOnInit();
  }
}
