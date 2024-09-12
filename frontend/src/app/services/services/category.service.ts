import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {CategoryResponse} from '../models/category-response';
import {deleteCategory1, DeleteCategory1$Params} from '../fn/category/delete-category-1';
import {existById1, ExistById1$Params} from '../fn/category/exist-by-id-1';
import {findAllCategory, FindAllCategory$Params} from '../fn/category/find-all-category';
import {findCategoryById, FindCategoryById$Params} from '../fn/category/find-category-by-id';
import {PageResponseCategoryResponse} from '../models/page-response-category-response';
import {saveCategory, SaveCategory$Params} from '../fn/category/save-category';
import {updateCategory, UpdateCategory$Params} from '../fn/category/update-category';

@Injectable({ providedIn: 'root' })
export class CategoryService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }
  static readonly FindCategoryByIdPath = '/categories/{categorie-id}';
  findCategoryById$Response(params: FindCategoryById$Params, context?: HttpContext): Observable<StrictHttpResponse<CategoryResponse>> {
    return findCategoryById(this.http, this.rootUrl, params, context);
  }
  findCategoryById(params: FindCategoryById$Params, context?: HttpContext): Observable<CategoryResponse> {
    return this.findCategoryById$Response(params, context).pipe(
      map((r: StrictHttpResponse<CategoryResponse>): CategoryResponse => r.body)
    );
  }
  static readonly UpdateCategoryPath = '/categories/{categorie-id}';
  updateCategory$Response(params: UpdateCategory$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateCategory(this.http, this.rootUrl, params, context);
  }
  updateCategory(params: UpdateCategory$Params, context?: HttpContext): Observable<void> {
    return this.updateCategory$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }
  static readonly DeleteCategory1Path = '/categories/{categorie-id}';
  deleteCategory1$Response(params: DeleteCategory1$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteCategory1(this.http, this.rootUrl, params, context);
  }

  deleteCategory1(params: DeleteCategory1$Params, context?: HttpContext): Observable<void> {
    return this.deleteCategory1$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  static readonly FindAllCategoryPath = '/categories';
  findAllCategory$Response(params?: FindAllCategory$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseCategoryResponse>> {
    return findAllCategory(this.http, this.rootUrl, params, context);
  }
  findAllCategory(params?: FindAllCategory$Params, context?: HttpContext): Observable<PageResponseCategoryResponse> {
    return this.findAllCategory$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseCategoryResponse>): PageResponseCategoryResponse => r.body)
    );
  }
  static readonly SaveCategoryPath = '/categories';
  saveCategory$Response(params: SaveCategory$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveCategory(this.http, this.rootUrl, params, context);
  }
  saveCategory(params: SaveCategory$Params, context?: HttpContext): Observable<number> {
    return this.saveCategory$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }
  static readonly ExistById1Path = '/categories/exists/{categorie-id}';
  existById1$Response(params: ExistById1$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return existById1(this.http, this.rootUrl, params, context);
  }
  existById1(params: ExistById1$Params, context?: HttpContext): Observable<boolean> {
    return this.existById1$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

}
