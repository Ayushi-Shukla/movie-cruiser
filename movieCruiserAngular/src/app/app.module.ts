import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule,Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ThumbnailComponent } from './modules/movie/components/thumbnail/thumbnail.component';
import { HttpInterceptorHandler } from '@angular/common/http/src/interceptor';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from './modules/movie/service/movie.service';
import { MovieModule } from './modules/movie/movie.module';
import { ContainerComponent } from 'src/app/modules/movie/components/container/container.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { WatchlistComponent } from 'src/app/modules/movie/components/watchlist/watchlist.component';
import { TmdbContainerComponent } from 'src/app/modules/movie/components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from 'src/app/modules/movie/components/movie-dialog/movie-dialog.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SearchComponent } from 'src/app/modules/movie/components/search/search.component';
import {MatIconModule} from '@angular/material/icon';
import { LoginComponent } from 'src/app/modules/authentication/login/login.component';
import { RegisterComponent } from 'src/app/modules/authentication/register/register.component'
import { AuthenticationRoutingModule } from './modules/authentication/authentication-router.module';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { AuthguardService } from './authguard.service';
import { AuthenticationModule } from './modules/authentication/authentication.module';

const appRoutes: Routes=[{
  path:'',
  redirectTo:'/login',
  pathMatch:'full',
}]
@NgModule({
  declarations: [
    AppComponent, MovieDialogComponent,WatchlistComponent,TmdbContainerComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,AppRoutingModule,HttpClientModule, MovieModule,
    RouterModule.forRoot(appRoutes), BrowserAnimationsModule, MatToolbarModule,
    MatButtonModule,MatDialogModule,FormsModule,MatInputModule,
    ReactiveFormsModule, MatFormFieldModule, AuthenticationRoutingModule,
    AuthenticationModule
  ],
  providers: [MovieService,AuthenticationService,AuthguardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
