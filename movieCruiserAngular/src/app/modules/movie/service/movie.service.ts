import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, retry } from 'rxjs/operators';
import { Movie } from 'src/app/modules/movie/class/movie'
import { Observable } from 'rxjs';


@Injectable()
export class MovieService{

    tmdbEndpoint: string; search:string;
    imagePrefix: string;
    apikey: string;
    springEndPoint: string;

    constructor(private http: HttpClient){
        this.apikey="eae8a1170a7f51651352d68eb382c103"
        this.tmdbEndpoint="https://api.themoviedb.org/3/movie"
        this.imagePrefix="https://image.tmdb.org/t/p/w500";
        this.springEndPoint='http://localhost:9898/api/v1/movieservice';
        this.search="https://api.themoviedb.org/3/search/movie?"
    }
   
   getMovies(type:string, page:number=1): Observable<Array<Movie>>{
        const endPoint=`${this.tmdbEndpoint}/${type}?api_key=${this.apikey}&page=${page}`
        return this.http.get(endPoint).pipe(
            map(this.pickMovieResults),
            map(this.transformPosterPath.bind(this))
        ); 
    }

    transformPosterPath(movies): Array<Movie>{
       return movies.map(movie=>{
            movie.poster_path=`${this.imagePrefix}${movie.poster_path}`;
            return movie;
        });
    }

    pickMovieResults(response){
        return response['results'];
    }

    addMovieToWatchList(movie){
        return this.http.post(this.springEndPoint+'/movie',movie);
    }

    getMyWatchList(): Observable<Array<Movie>>{
        return this.http.get<Array<Movie>>(this.springEndPoint+'/movies');
    }

    deleteFromMyWatchList(movie:Movie){
        const url=`${this.springEndPoint}/movie/${movie.id}`;
        return this.http.delete(url,{responseType:'text'});
    }

    updateComments(movie){
        return this.http.put(this.springEndPoint+'/movie',movie);
    }

    searchMovies(searchKey: string): Observable<Array<Movie>>{
        if(searchKey.length>0){
            const url =`${this.search}api_key=${this.apikey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
            return this.http.get(url).pipe(
                map(this.pickMovieResults),
                map(this.transformPosterPath.bind(this))
            ); 
        }
    }
}