import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoriesComponent } from './pages/categories/categories.component';
import { DivisionsComponent } from './pages/divisions/divisions.component';
import { TownshipsComponent } from './pages/townships/townships.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoriesComponent,
    DivisionsComponent,
    TownshipsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
