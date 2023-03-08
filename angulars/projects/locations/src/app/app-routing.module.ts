import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriesComponent } from './pages/categories/categories.component';
import { DivisionsComponent } from './pages/divisions/divisions.component';
import { TownshipsComponent } from './pages/townships/townships.component';

const routes: Routes = [
  {path: 'categories', component: CategoriesComponent},
  {path: 'divisions', component: DivisionsComponent},
  {path: 'townships', component: TownshipsComponent},
  {path: '', redirectTo: 'categories', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
