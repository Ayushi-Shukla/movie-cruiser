import { Component, OnInit, Inject } from '@angular/core';
import { Movie } from '../../class/movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MovieModule } from '../../movie.module';
import { MovieService } from '../../service/movie.service';

@Component({
  selector: 'app-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {
  movie: Movie;
  comments: string;
  actionType: string;

  constructor(public snackbar: MatSnackBar,public dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieservice: MovieService)
  {
    this.comments=data.obj.comments;
    this.movie=data.obj;
    this.actionType=data.actionType;
  }

  ngOnInit() {
    console.log(this.data);
  }

  onNoClick(){
    this.dialogRef.close();
  }

  updateComments(){
    console.log("comments:",this.comments);
    this.movie.comments=this.comments;
    this.movieservice.updateComments(this.movie).subscribe(movie=>{
      this.snackbar.open("Movie updated to watchlist successfully", "",{
        duration:2000,
      });
    })
  }
}
