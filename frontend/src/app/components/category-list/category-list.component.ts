import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";
import {PageResponseCategoryResponse} from "../../services/models/page-response-category-response";
import {CategoryService} from "../../services/services/category.service";
import {NgForOf, NgIf} from "@angular/common";
import {CategoryRequest} from "../../services/models/category-request";

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [
    RouterOutlet,
    NgForOf,
    NgIf
  ],
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css'
})
  export class CategoryListComponent implements OnInit{

    categoryResponse: PageResponseCategoryResponse = {}
    page = 0;
    size = 3;
    pages: any = [];
    constructor(
      private categoryService: CategoryService,
      private router: Router
    ) {
    }
    ngOnInit() {
      this.findAllCategory()
    }

    private findAllCategory(){
        this.categoryService.findAllCategory({
          page: this.page,
          size: this.size
        }).subscribe({
          next: (categories) => {
            this.categoryResponse = categories
          }
        })
    }

  deleteCategory(categoryId: number) {
    this.categoryService.deleteCategory1({ 'categorie-id': categoryId }).subscribe({
      next: () => {
        console.log('Catégorie supprimée avec succès');
        // Rafraîchir la liste des catégories après suppression
        this.findAllCategory();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression de la catégorie', err);
      }
    });
  }



  gotToPage(page: number) {
    this.page = page;
    this.findAllCategory();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllCategory();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllCategory();
  }

  goToLastPage() {
    this.page = this.categoryResponse.totalPages as number - 1;
    this.findAllCategory();
  }

  goToNextPage() {
    this.page++;
    this.findAllCategory();
  }

  get isLastPage() {
    return this.page === this.categoryResponse.totalPages as number - 1;
  }
}
