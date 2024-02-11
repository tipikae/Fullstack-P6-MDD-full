import { Component, Input, OnDestroy } from '@angular/core';
import { Topic } from '../../models/topic.model';
import { TopicService } from '../../services/topic.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';

/**
 * My topics component.
 */
@Component({
  selector: 'app-my-topics',
  templateUrl: './my-topics.component.html',
  styleUrl: './my-topics.component.css'
})
export class MyTopicsComponent implements OnDestroy {

  private unsubscribeSubscription!: Subscription;

  public onError: boolean = false;

  @Input() topics!: Topic[];

  /**
   * MyTopics constructor.
   * @param topicService Topics service.
   * @param matSnackBar Material snack bar.
   */
  constructor (private topicService: TopicService,
               private matSnackBar: MatSnackBar) {}

  /**
   * Call on destroy.
   */
  ngOnDestroy(): void {
    if (this.unsubscribeSubscription != undefined) this.unsubscribeSubscription.unsubscribe();
  }

  /**
   * 
   * @param id Unsubscribe to a topic.
   */
  public unsubscribe(id: number): void {
    this.unsubscribeSubscription = this.topicService.unsubscribe(id).subscribe({
      next: _ => {
        this.matSnackBar.open('Unsubscribe success !', 'Close', { duration: 3000 });
        this.topics = this.topics.filter(topic => topic.id != id);
      },
      error: _ => this.onError = true
    });
  }
}
