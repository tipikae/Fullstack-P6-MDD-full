import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyTopicsComponent } from './components/my-topics/my-topics.component';
import { ListComponent } from './components/list/list.component';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    MyTopicsComponent,
    ListComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    MyTopicsComponent
  ]
})
export class TopicsModule { }
