import { Component, Input, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { BehaviorSubject } from 'rxjs';
import { Comment } from '../../models/comment.model';

@Component({
  selector: 'app-comments',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {

  public comments$ = new BehaviorSubject<Comment[]>([]);

  @Input() postId!: number;

  constructor (private commentService: CommentService) {}
  
  ngOnInit(): void {
    this.commentService.getComments(this.postId).subscribe({
      next: (comments: Comment[]) => this.comments$.next(comments)
    });
  }

  public refreshList($event: boolean): void {
    this.ngOnInit();
  }
}
