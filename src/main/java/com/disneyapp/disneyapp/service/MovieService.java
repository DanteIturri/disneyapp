package com.disneyapp.disneyapp.service;

import com.disneyapp.disneyapp.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {
    MovieDTO save(MovieDTO dto);
    void addCharacterToMovie(Long movieId, Long charId);
    void addGenreToMovie(Long movieId, Long genreId);

    List<MovieDTO> getAllMovie();
    MovieDTO getMovieDetails(Long id);

    void deleteMovieById(Long id);

    // PUT
    MovieDTO editMovieById(Long id, MovieDTO movieToEdit);

    // FILTERS
    List<MovieDTO> getByFilters(String name, Set<Long> genre, String order);


}
