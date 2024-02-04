import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { BehaviorSubject } from 'rxjs';
import { Post } from '../../models/post.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {

  public posts$: BehaviorSubject<Post[]> = new BehaviorSubject<Post[]>([]);
  public onError: boolean = false;
  public order: string = "desc";

  constructor (private postService: PostService,
               private router: Router) {}
  
  ngOnInit(): void {
    this.postService.getPosts(this.order).subscribe({
      next: (posts: Post[]) => this.posts$.next(posts)
    });
  }

  public sortList(order: string): void {
    this.order = order;
    this.ngOnInit();
  }
}
