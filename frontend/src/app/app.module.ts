import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateComponent } from './scheduling/create/create.component';
import { ListComponent } from './scheduling/list/list.component';
import { SchedulingModule } from './scheduling/scheduling.module';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  {
    path: 'create', 
    component: CreateComponent
  },
  {
    path: 'list',
    component: ListComponent
  },
  {
    path: '',
    redirectTo: 'list',
    pathMatch: 'full'
  }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot(routes),
    SchedulingModule,
    HttpClientModule,
    AppRoutingModule   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
