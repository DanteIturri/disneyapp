package com.disneyapp.disneyapp.service.impl;

import com.disneyapp.disneyapp.dto.MovieDTO;
import com.disneyapp.disneyapp.dto.MovieFiltersDTO;
import com.disneyapp.disneyapp.entity.CharacterEntity;
import com.disneyapp.disneyapp.entity.GenreEntity;
import com.disneyapp.disneyapp.entity.MovieEntity;

import com.disneyapp.disneyapp.exception.ParamNotFound;
import com.disneyapp.disneyapp.mapper.MovieMapper;
import com.disneyapp.disneyapp.repository.MovieRepository;
import com.disneyapp.disneyapp.repository.specifications.MovieSpecifications;
import com.disneyapp.disneyapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CharacterServiceImpl characterService;

    @Autowired
    GenreServiceImpl genreServ;

    @Autowired
    MovieSpecifications movieSpecs;

    public MovieDTO save(MovieDTO dto){
        MovieEntity entity = movieMapper.MovieDTO2Entity(dto);
        MovieEntity saveEntity = movieRepository.save(entity);
        MovieDTO result = movieMapper.MovieEntity2DTO(saveEntity,false);

        return result;
    }

    @Override
    public void addCharacterToMovie(Long movieId, Long charId) {
        MovieEntity savedMovie = this.handleFindById(movieId);
        CharacterEntity savedChar = characterService.handleFindById(charId);
        savedMovie.getMovieCharacters().size();
        savedMovie.addCharacterToMovie(savedChar);
        movieRepository.save(savedMovie);
    }

    @Override
    public void addGenreToMovie(Long movieId, Long genreId) {
        MovieEntity savedMovie = this.handleFindById(movieId);
        GenreEntity savedGenre = genreServ.handleFindById(genreId);
        savedMovie.getMovieGenres().size();
        savedMovie.addGenreToMovie(savedGenre);
        movieRepository.save(savedMovie);
    }

    public List<MovieDTO> getAllMovie(){
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.EntityList2DTOList(entities,false);
        return result;
    }

    @Override
    public MovieDTO getMovieDetails(Long id) {
        MovieEntity dbMovie = this.handleFindById(id);
        MovieDTO resultDTO = movieMapper.MovieEntity2DTO(dbMovie, true);
        return resultDTO;
    }


    // --- PUT ---
    @Override
    public MovieDTO editMovieById(Long id, MovieDTO movieToEdit) {
        MovieEntity savedMovie = this.handleFindById(id);
        savedMovie.setImage(movieToEdit.getImage());
        savedMovie.setTitle(movieToEdit.getTitle());
        savedMovie.setRating(movieToEdit.getRating());
        savedMovie.setCreationDate(movieMapper.String2LocalDate(movieToEdit.getCreationDate()));
        MovieEntity editedMovie = movieRepository.save(savedMovie);
        MovieDTO resultDTO = movieMapper.MovieEntity2DTO(editedMovie, false);
        return resultDTO;
    }

    // --- DELETE ---
    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    // --- FILTERS ---
    @Override
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {
        MovieFiltersDTO movieFilters = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entityList = movieRepository.findAll( movieSpecs.getFiltered(movieFilters));
        List<MovieDTO> resultDTO = movieMapper.EntityList2DTOList(entityList, true);
        return resultDTO;
    }


    public MovieEntity handleFindById(Long id) {
        Optional<MovieEntity> toBeFound = movieRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Movie for id: " + id);
        }
        return toBeFound.get();
    }
}

