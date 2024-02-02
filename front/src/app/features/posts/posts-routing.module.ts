import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/list/list.component';
import { DetailComponent } from './components/detail/detail.component';
import { FormComponent } from './components/form/form.component';

const routes: Routes = [
  { title: 'Feed', path: '', component: ListComponent },
  { title: 'Detail', path: 'detail', component: DetailComponent },
  { title: 'Create', path: 'create', component: FormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }
