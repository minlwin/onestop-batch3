import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConstructingComponent } from './pages/reactives/constructing/constructing.component';
import { DynamicArrayComponent } from './pages/reactives/dynamic-array/dynamic-array.component';
import { DynamicGroupComponent } from './pages/reactives/dynamic-group/dynamic-group.component';
import { ReactivesComponent } from './pages/reactives/reactives.component';
import { ValidationsComponent } from './pages/reactives/validations/validations.component';
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
  {path: 'reactives', component: ReactivesComponent, children: [
    {path: 'construct', component: ConstructingComponent},
    {path: 'validation', component: ValidationsComponent},
    {path: 'array', component: DynamicArrayComponent},
    {path: 'group', component: DynamicGroupComponent},
    {path: '', redirectTo: 'construct', pathMatch: 'full'}
  ]},
  {path: '', redirectTo: 'templates', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
