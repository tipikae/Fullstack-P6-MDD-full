import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TopicComponent } from './features/topic/topic.component';
import { CoreModule } from './core/core.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { MeComponent } from './components/me/me.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { MyTopicsComponent } from './features/topics/components/my-topics/my-topics.component';
import { ListComponent } from './features/topics/components/list/list.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    TopicComponent,
    MyTopicsComponent,
    ListComponent,
    MeComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule,
    BrowserAnimationsModule,
    SharedModule,
    FlexLayoutModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
