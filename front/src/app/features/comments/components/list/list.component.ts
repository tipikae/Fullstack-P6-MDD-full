import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { BehaviorSubject, Subscription } from 'rxjs';
import { Comment } from '../../models/comment.model';

/**
 * Comments list component.
 */
@Component({
  selector: 'app-comments',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit, OnDestroy {

  private getCommentsSubscription!: Subscription;

  public comments$ = new BehaviorSubject<Comment[]>([]);

  @Input() postId!: number;

  /**
   * Comments ListComponent constructor.
   * @param commentService Comment service.
   */
  constructor (private commentService: CommentService) {}
  
  /**
   * Init component.
   */
  ngOnInit(): void {
    this.getCommentsSubscription = this.commentService.getComments(this.postId).subscribe({
      next: (comments: Comment[]) => this.comments$.next(comments)
    });
  }

  /** Call on destroy. */
  ngOnDestroy(): void {
    if (this.getCommentsSubscription != undefined) this.getCommentsSubscription.unsubscribe();
  }

  /**
   * Refresh comments list.
   * @param $event boolean.
   */
  public refreshList($event: boolean): void {
    this.ngOnInit();
  }
}
