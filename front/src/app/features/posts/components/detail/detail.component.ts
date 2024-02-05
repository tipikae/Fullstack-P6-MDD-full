import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../models/post.model';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css'
})
export class DetailComponent implements OnInit {

  public onError = false;
  public post: Post | undefined;
  public postId!: number;

  constructor (private postService: PostService,
               private router: Router,
               private activatedRoute: ActivatedRoute) {}
  
  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id != null) {
      this.postId = +id;
      this.postService.getPost(+id).subscribe({
        next: (post: Post) => this.post = post,
        error: _ => this.onError = true
      });
    } else {
      this.onError = true;
    }
  }
}
