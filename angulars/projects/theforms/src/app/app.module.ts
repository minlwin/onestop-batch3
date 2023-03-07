import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TemplatesComponent } from './pages/templates/templates.component';
import { ReactivesComponent } from './pages/reactives/reactives.component';
import { FormsModule } from '@angular/forms';
import { TopFormComponent } from './pages/templates/top-form/top-form.component';
import { GroupFormComponent } from './pages/templates/group-form/group-form.component';
import { TemplateDataBindComponent } from './pages/templates/template-data-bind/template-data-bind.component';
import { CardComponent } from './widgets/card/card.component';
import { FormItemComponent } from './widgets/form-item/form-item.component';

@NgModule({
  declarations: [
    AppComponent,
    TemplatesComponent,
    ReactivesComponent,
    TopFormComponent,
    GroupFormComponent,
    TemplateDataBindComponent,
    CardComponent,
    FormItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
