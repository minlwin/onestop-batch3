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
    DivisionEditComponent
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
