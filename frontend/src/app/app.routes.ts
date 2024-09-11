import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'categories',
    loadChildren: () => import('./modules/category/category.module').then(m => m.CategoryModule)
  }
];
