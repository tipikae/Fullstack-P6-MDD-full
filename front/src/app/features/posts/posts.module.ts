import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { ListComponent } from './components/list/list.component';
import { FormComponent } from './components/form/form.component';
import { DetailComponent } from './components/detail/detail.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CommentsModule } from '../comments/comments.module';
import { TopicsModule } from '../topics/topics.module';


@NgModule({
  declarations: [
    ListComponent,
    FormComponent,
    DetailComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    SharedModule,
    CommentsModule,
    TopicsModule
  ]
})
export class PostsModule { }
