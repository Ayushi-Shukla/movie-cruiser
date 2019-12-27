import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/modules/movie/class/movie'
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {
  movies: Array<Movie>;
  movieType: string;
  
  constructor(private movieservice: MovieService,private route:ActivatedRoute) {
    this.movies=[];
    this.route.data.subscribe((data)=>{
      this.movieType=data.movieType;
    });  }

  ngOnInit() {
    this.movieservice.getMovies(this.movieType).subscribe((movies)=>{
      this.movies.push(...movies);
  });
  }

}
