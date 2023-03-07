import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReactivesComponent } from './pages/reactives/reactives.component';
import { GroupFormComponent } from './pages/templates/group-form/group-form.component';
import { TemplateDataBindComponent } from './pages/templates/template-data-bind/template-data-bind.component';
import { TemplatesComponent } from './pages/templates/templates.component';
import { TopFormComponent } from './pages/templates/top-form/top-form.component';

const routes: Routes = [
  {path: 'templates', component: TemplatesComponent, children: [
    {path: 'top', component: TopFormComponent},
    {path: 'group', component: GroupFormComponent},
    {path: 'bind', component: TemplateDataBindComponent},
    {path: '', redirectTo: 'top', pathMatch: 'full'}
  ]},
  {path: 'reactive', component: ReactivesComponent},
  {path: '', redirectTo: 'templates', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
