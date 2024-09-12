import { Routes } from '@angular/router';
import {MainComponent} from "./main/main.component";
import {CategoryListComponent} from "./components/category-list/category-list.component";


export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: 'categories',
        component: CategoryListComponent
      }
    ]
  }
];
