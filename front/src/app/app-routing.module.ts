import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NotFoundComponent } from "./components/not-found/not-found.component";
import { HomeComponent } from "./components/home/home.component";
import { MeComponent } from "./components/me/me.component";
import { unauthGuard } from "./guards/unauth.guard";
import { authGuard } from "./guards/auth.guard";
import { ListComponent } from "./features/topics/components/list/list.component";

const routes: Routes = [
    { title: 'Home', path: '', canActivate: [unauthGuard], component: HomeComponent },
    {
        path: 'auth',
        canActivate: [unauthGuard],
        loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
    },
    {
        path: 'posts',
        canActivate: [authGuard],
        loadChildren: () => import('./features/posts/posts.module').then(m => m.PostsModule)
    },
    { title: 'Profile', path: 'me', canActivate: [authGuard], component: MeComponent },
    { title: 'Topics', path: 'topics', canActivate: [authGuard], component: ListComponent },
    { title: 'Not found', path: '404', component: NotFoundComponent },
    { path: '**', redirectTo: '404' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}