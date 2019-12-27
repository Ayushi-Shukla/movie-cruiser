import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/modules/movie/class/movie'
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  movies: Array<Movie>
  useWatchlistApi=true;

  constructor(private movieservice: MovieService,private route:ActivatedRoute, private snackbar: MatSnackBar)
  {
    this.movies=[];
  }

  ngOnInit() {
    let message='Watch list is empty';
    this.movieservice.getMyWatchList().subscribe(movies=>{
      if (movies.length===0){
        this.snackbar.open(message,'',{
          duration:1000
        })
      }
      this.movies.push(...movies);
    })
  }

}
