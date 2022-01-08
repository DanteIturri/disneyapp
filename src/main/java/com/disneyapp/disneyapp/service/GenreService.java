package com.disneyapp.disneyapp.service;

import com.disneyapp.disneyapp.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    //Method Post
    GenreDTO save(GenreDTO dto);

    List<GenreDTO> getAllGenre();

    void deletedGenreByid(Long id);

    GenreDTO editGenreById(Long id, GenreDTO genreToEdit);
}
