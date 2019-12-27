import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ThumbnailComponent } from './modules/movie/components/thumbnail/thumbnail.component';
import { ContainerComponent } from './modules/movie/components/container/container.component';
import { TmdbContainerComponent } from './modules/movie/components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './modules/movie/components/watchlist/watchlist.component';
import { SearchComponent } from './modules/movie/components/search/search.component';
import { AuthguardService } from './authguard.service';

const routes: Routes = [
  {
    path:'movies',
    children:[
      {
        path: '',
        redirectTo: '/movies/popular',
        pathMatch: 'full',
        canActivate:[AuthguardService]
      },
      {
        path: 'popular',
        component: TmdbContainerComponent,
        canActivate:[AuthguardService],
        data: {movieType: 'popular'}
      },
      {
        path: 'top_rated',
        component: TmdbContainerComponent,
        canActivate:[AuthguardService],
        data: {movieType: 'top_rated'}
      },
      {
        path:'watchlist',
        component: WatchlistComponent,
        canActivate:[AuthguardService]
      },
      {
        path:'search',
        component: SearchComponent,
        canActivate:[AuthguardService]
      }
    ]
  }];
  
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
