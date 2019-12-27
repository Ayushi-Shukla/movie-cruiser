import { Component, OnInit, Input } from '@angular/core';
import { Movie } from 'src/app/modules/movie/class/movie'
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  movies: Array<Movie>;

  @Input()
  useWatchlistApi: boolean;
  
  movie=new Movie();
  constructor(private movieservice: MovieService, private snackbar: MatSnackBar) {
  }

  ngOnInit() {
  }

  addMovieToWatchList(movie){
    let message=`${movie.title} add to watchlist`;
    console.log("movie title", movie.title);
    movie.movieId=movie.id;
    console.log(movie.movieId);
    console.log(movie);
    this.movieservice.addMovieToWatchList(movie).subscribe(movie=>{
      this.snackbar.open(message, '', {
        duration:1000
      });
    })
  }


  deleteMovieFromWatchList(movie){
    let message=`${movie.title} deleted from watchlist`;
    this.movieservice.deleteFromMyWatchList(movie).subscribe((movie)=>{
      this.snackbar.open(message, '', {
        duration:1000
      });
    });
    const index = this.movies.indexOf(movie);
    this.movies.splice(index,1);
  }
}

