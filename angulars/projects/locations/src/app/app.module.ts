import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoriesComponent } from './pages/categories/categories.component';
import { DivisionsComponent } from './pages/divisions/divisions.component';
import { TownshipsComponent } from './pages/townships/townships.component';
import { ModalDialogComponent } from './widgets/modal-dialog/modal-dialog.component';
import { CategoryEditComponent } from './pages/categories/category-edit/category-edit.component';
import { FormItemComponent } from './widgets/form-item/form-item.component';
import { NoDataComponent } from './widgets/no-data/no-data.component';
import { FormItemErrorComponent } from './widgets/form-item-error/form-item-error.component';
import { DivisionEditComponent } from './pages/divisions/division-edit/division-edit.component';
import { TopPageComponent } from './widgets/top-page/top-page.component';
import { TownshipSearchComponent } from './pages/townships/township-search/township-search.component';
import { DivisionItemComponent } from './pages/divisions/division-item/division-item.component';
import { CategoryItemComponent } from './pages/categories/category-item/category-item.component';
import { DivisionSearchComponent } from './pages/divisions/division-search/division-search.component';
import { TownshipEditComponent } from './pages/townships/township-edit/township-edit.component';
import { TownshipItemComponent } from './pages/townships/township-item/township-item.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoriesComponent,
    DivisionsComponent,
    TownshipsComponent,
    ModalDialogComponent,
    CategoryEditComponent,
    FormItemComponent,
    NoDataComponent,
    FormItemErrorComponent,
    DivisionEditComponent,
    TopPageComponent,
    TownshipSearchComponent,
    DivisionItemComponent,
    CategoryItemComponent,
    DivisionSearchComponent,
    TownshipEditComponent,
    TownshipItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
