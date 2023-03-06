import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { InterpolationsComponent } from './pages/interpolations/interpolations.component';
import { TowWayComponent } from './pages/tow-way/tow-way.component';
import { PropertiesComponent } from './pages/one-way/properties/properties.component';
import { AttributesComponent } from './pages/one-way/attributes/attributes.component';
import { StyleClassComponent } from './pages/one-way/style-class/style-class.component';
import { EventsComponent } from './pages/one-way/events/events.component';
import { CompPropertiesComponent } from './pages/interpolations/comp-properties/comp-properties.component';
import { InputVariablesComponent } from './pages/interpolations/input-variables/input-variables.component';
import { TempReferencesComponent } from './pages/interpolations/temp-references/temp-references.component';
import { CardComponent } from './pages/one-way/properties/card/card.component';
import { BackColorDirective } from './pages/one-way/properties/back-color.directive';
import { CalButtonComponent } from './pages/one-way/events/cal-button/cal-button.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    InterpolationsComponent,
    TowWayComponent,
    PropertiesComponent,
    AttributesComponent,
    StyleClassComponent,
    EventsComponent,
    CompPropertiesComponent,
    InputVariablesComponent,
    TempReferencesComponent,
    CardComponent,
    BackColorDirective,
    CalButtonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
