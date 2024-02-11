import { Component, OnDestroy, OnInit } from '@angular/core';
import { TopicService } from '../../services/topic.service';
import { Topic } from '../../models/topic.model';
import { BehaviorSubject, Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

/**
 * Topics list component.
 */
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit, OnDestroy {

  private getTopicsSubscription!: Subscription;
  private getProfileSubscription!: Subscription;
  private subscribeSubscription!: Subscription;

  public topics$: BehaviorSubject<Topic[]> = new BehaviorSubject<Topic[]>([]);
  public subscribed: boolean[] = [];

  /**
   * ListComponent constructor.
   * @param topicService Topic service.
   * @param userService User service.
   * @param matSnackBar Material snack bar.
   */
  constructor (private topicService: TopicService,
               private userService: UserService,
               private matSnackBar: MatSnackBar) {}

  /**
   * Init component.
   */
  ngOnInit(): void {
    this.initTopics();
  }

  /**
   * Call on destroy.
   */
  ngOnDestroy(): void {
    if (this.getTopicsSubscription != undefined) this.getTopicsSubscription.unsubscribe();
    if (this.getProfileSubscription != undefined) this.getProfileSubscription.unsubscribe();
    if (this.subscribeSubscription != undefined) this.subscribeSubscription.unsubscribe();
  }

  /**
   * Subscribe to a topic.
   * @param id Topic id.
   */
  subscribe(id: number): void {
    this.subscribeSubscription = this.topicService.subscribe(id).subscribe({
      next: _ => {
        this.matSnackBar.open('Subscription success !', 'Close', { duration: 3000 });
        this.initTopics();
      },
      error: _ => this.matSnackBar.open('Subscription failed', 'Close', { duration: 3000 })
    });
  }

  /**
   * Initialize topics list.
   */
  private initTopics(): void {
    this.getTopicsSubscription = this.topicService.getTopics().subscribe({
      next: (topics: Topic[]) => {
        this.getProfileSubscription = this.userService.getProfile().subscribe({
          next: (user: User) => {
            this.setSubscribed(topics, user);
            this.topics$.next(topics);
          }
        });
      }
    });
  }

  /**
   * Set already topics subscribed by user.
   * @param topics Topics list.
   * @param user Current user.
   */
  private setSubscribed(topics: Topic[], user: User): void {
    let myTopicIds = user.topics.map(topic => topic.id);
    this.subscribed = [];
    topics.forEach(topic => {
      if (myTopicIds.includes(topic.id)) {
        this.subscribed[topic.id] = true;
      } else {
        this.subscribed[topic.id] = false;
      }
    });
  }
}
