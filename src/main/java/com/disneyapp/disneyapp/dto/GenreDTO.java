package com.disneyapp.disneyapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenreDTO {

    private Long id;

    private String image;

    private String name;

    private boolean deleted;

    private List<MovieDTO> genreMovies;
}
