package com.disneyapp.disneyapp.controller;

import com.disneyapp.disneyapp.dto.MovieDTO;
import com.disneyapp.disneyapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    //All Movie
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAllMovie(){
        List<MovieDTO> moviesList = movieService.getAllMovie();
        return ResponseEntity.ok().body(moviesList);
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id){
        MovieDTO movie = movieService.getMovieDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
    //Save Movie
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto){
        MovieDTO movieSave = movieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSave);
    }
    @PostMapping("/{movieId}/character/{charId}")
    public ResponseEntity<Void> addChar(@PathVariable Long movieId, @PathVariable Long charId){
        movieService.addCharacterToMovie(movieId, charId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{movieId}/genre/{genreId}")
    public ResponseEntity<Void> addGenre(@PathVariable Long movieId, @PathVariable Long genreId){
        movieService.addGenreToMovie(movieId, genreId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // == PUT ==
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@PathVariable Long id, @RequestBody MovieDTO movieToEdit){
        MovieDTO editedMovie = movieService.editMovieById(id, movieToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedMovie);
    }

    // == DELETE ==
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        movieService.deleteMovieById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // == FILTERS ==
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> filteredMovies = movieService.getByFilters(title, genre, order);
        return ResponseEntity.status(HttpStatus.OK).body(filteredMovies);
    }
}
