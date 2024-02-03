import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/list/list.component';
import { DetailComponent } from './components/detail/detail.component';
import { FormComponent } from './components/form/form.component';

const routes: Routes = [
  { title: 'Feed', path: '', component: ListComponent },
  { title: 'Create', path: 'create', component: FormComponent },
  { title: 'Detail', path: ':id', component: DetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }
