import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CompPropertiesComponent } from './pages/interpolations/comp-properties/comp-properties.component';
import { InputVariablesComponent } from './pages/interpolations/input-variables/input-variables.component';
import { InterpolationsComponent } from './pages/interpolations/interpolations.component';
import { TempReferencesComponent } from './pages/interpolations/temp-references/temp-references.component';
import { AttributesComponent } from './pages/one-way/attributes/attributes.component';
import { EventsComponent } from './pages/one-way/events/events.component';
import { PropertiesComponent } from './pages/one-way/properties/properties.component';
import { StyleClassComponent } from './pages/one-way/style-class/style-class.component';
import { TowWayComponent } from './pages/tow-way/tow-way.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'inter', component: InterpolationsComponent, children: [
    {path: 'props', component: CompPropertiesComponent},
    {path: 'inputs', component: InputVariablesComponent},
    {path: 'refs', component: TempReferencesComponent},
    {path: '', redirectTo: 'props', pathMatch: 'full'}
  ]},
  {path: 'two', component: TowWayComponent},
  {path: 'one', children: [
    {path: 'props', component: PropertiesComponent},
    {path: 'attrs', component: AttributesComponent},
    {path: 'styles', component: StyleClassComponent},
    {path: 'events', component: EventsComponent},
  ]},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
