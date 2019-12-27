import { Component, OnInit } from '@angular/core';
import { Movie } from '../../class/movie';
import { MovieService } from '../../service/movie.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  movies: Array<Movie>;

  constructor(private movieservice: MovieService) { }

  ngOnInit() {
  }

  onEnter(searchKey){
    console.log('Search Key: ',searchKey);
    this.movieservice.searchMovies(searchKey).subscribe(movies=>{
      this.movies=movies;
    })
  }
}
