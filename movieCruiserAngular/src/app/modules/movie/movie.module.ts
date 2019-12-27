import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ContainerComponent } from './components/container/container.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { MovieService } from './service/movie.service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon'
import { TokenInterceptor } from 'src/app/modules/movie/interceptor.service';

@NgModule({
  declarations: [ThumbnailComponent,  ContainerComponent],
  imports: [
    CommonModule, HttpClientModule, AppRoutingModule, MatCardModule,
    MatButtonModule,MatSnackBarModule,MatDialogModule, 
    MatDialogModule, MatInputModule,FormsModule,
    ReactiveFormsModule, MatFormFieldModule,MatIconModule
  ],
  entryComponents:[MovieDialogComponent],
  exports:[AppRoutingModule, 
    ThumbnailComponent, ContainerComponent
  ],
  providers:[MovieService,{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
})
export class MovieModule { }
