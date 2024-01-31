import { Component, Input } from '@angular/core';
import { Topic } from '../../models/topic.model';
import { TopicService } from '../../services/topic.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-my-topics',
  templateUrl: './my-topics.component.html',
  styleUrl: './my-topics.component.css'
})
export class MyTopicsComponent {

  public onError: boolean = false;

  @Input() topics!: Topic[];

  constructor (private topicService: TopicService,
               private matSnackBar: MatSnackBar) {}

  public unsubscribe(id: number): void {
    this.topicService.unsubscribe(id).subscribe({
      next: _ => {
        this.matSnackBar.open('Unsubscribe success !', 'Close', { duration: 3000 });
        this.topics = this.topics.filter(topic => topic.id != id);
      },
      error: _ => this.onError = true
    });
  }
}
