import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from 'src/app/modules/movie/class/movie'
import { HttpClient } from '@angular/common/http';
import { MovieService } from '../../service/movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  
  @Input()
  movie: Movie;

  @Input()
  useWatchlistApi: boolean;

  @Output()
  addMovie=new EventEmitter();

  @Output()
  deleteMovie=new EventEmitter();

  @Output()
  updateMovie=new EventEmitter();

  constructor(private snackbar: MatSnackBar, public dialog: MatDialog) {
  }

  ngOnInit() {
  }

  addToWatchlist(){
    this.addMovie.emit(this.movie);
    // console.log("movie object",this.movie);
    // let message=`${this.movie.title} add to watchlist`;
    // console.log("movie title",this.movie.title);
    // this.movieservice.addMovieToWatchList(this.movie).subscribe(movie=>{
    //   this.snackbar.open(message,'',{
    //     duration:1000
    //   });
    // })
  }

  deleteFromWatchlist(){
    console.log('Movie Deleted',this.movie.id);
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchlist(actionType){
    console.log('Movie is getting updated');
    let dialogRef=this.dialog.open(MovieDialogComponent,{
      width:'400px',
      data: { obj: this.movie, actionType:actionType}
    });
    console.log("open dialog");
    dialogRef.afterClosed().subscribe(result=>{
      console.log('The dialog was closed');
    });
  }
}
