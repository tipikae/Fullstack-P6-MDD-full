import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../services/topic.service';
import { Topic } from '../../models/topic.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent implements OnInit {

  public topics$: BehaviorSubject<Topic[]> = new BehaviorSubject<Topic[]>([]);
  public subscribed: boolean[] = [];

  constructor (private topicService: TopicService,
               private userService: UserService,
               private matSnackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.topicService.getTopics().subscribe({
      next: (topics: Topic[]) => {
        this.userService.getProfile().subscribe({
          next: (user: User) => {
            this.setSubscribed(topics, user);
            this.topics$.next(topics);
          }
        });
      }
    });
  }

  subscribe(id: number): void {
    this.topicService.subscribe(id).subscribe({
      next: _ => {
        this.subscribed[id] = true;
        this.matSnackBar.open('Subscription success !', 'Close', { duration: 3000 });
      },
      error: _ => this.matSnackBar.open('Subscription failed', 'Close', { duration: 3000 })
    });
  }

  private setSubscribed(topics: Topic[], user: User): void {
    let myTopicIds = user.topics.map(topic => topic.id);
    topics.forEach(topic => {
      if (myTopicIds.includes(topic.id)) {
        this.subscribed[topic.id] = true;
      } else {
        this.subscribed[topic.id] = false;
      }
    });
  }
}
