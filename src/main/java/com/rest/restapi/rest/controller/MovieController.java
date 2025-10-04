package com.rest.restapi.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restapi.rest.model.Movie;
import com.rest.restapi.rest.repository.MovieRepository;
@RestController
@RequestMapping("/api/movies")

public class MovieController {
    @Autowired //inject repo dependency
    private MovieRepository mR;

    //GET API ->
    @GetMapping
    public List<Movie> getAllMovies(){
        return mR.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        Optional<Movie> movie=mR.findById(id);
        if(movie.isPresent()){
            return ResponseEntity.ok(movie.get()); //200 OK
        }else{
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    //POST API->
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201 Created
    public Movie createMovie(@RequestBody Movie movie){
        return mR.save(movie);
    }

    //PUT API->
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id,@RequestBody Movie md){
        return mR.findById(id).map(movie->{
            movie.setTitle(md.getTitle());
            movie.setDirector(md.getDirector());
            movie.setYear(md.getYear());
            Movie updatedMovie= mR.save(movie);
            return ResponseEntity.ok(updatedMovie); // sould get HTTP 200 OK
        }).orElseGet(()-> ResponseEntity.notFound().build()); // HTTP 404 NOT FOUND
    }

    //Delete API->
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id){
        return mR.findById(id).map(movie->{
            mR.delete(movie);
            return ResponseEntity.ok().build();
        })
        .orElseGet(()->ResponseEntity.notFound().build()); 
    }

    //Patch API->
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> patchMovie(@PathVariable Long id, @RequestBody Movie partialUpdate) {
    return mR.findById(id)
        .map(existingMovie -> {
            if (partialUpdate.getTitle() != null) {
                existingMovie.setTitle(partialUpdate.getTitle());
            }
            if (partialUpdate.getDirector() != null) {
                existingMovie.setDirector(partialUpdate.getDirector());
            }
            if (partialUpdate.getYear() != null) {
                existingMovie.setYear(partialUpdate.getYear());
            }
            Movie updatedMovie = mR.save(existingMovie);
            return ResponseEntity.ok(updatedMovie);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
}


}