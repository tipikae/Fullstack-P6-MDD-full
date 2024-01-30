import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NotFoundComponent } from "./components/not-found/not-found.component";
import { HomeComponent } from "./components/home/home.component";
import { TopicComponent } from "./features/topic/topic.component";
import { MeComponent } from "./components/me/me.component";

const routes: Routes = [
    { path: '', component: HomeComponent },
    {
        path: 'auth',
        loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
    },
    { path: 'me', component: MeComponent },
    { path: 'topics', component: TopicComponent },
    { path: '404', component: NotFoundComponent },
    { path: '**', redirectTo: '404' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}