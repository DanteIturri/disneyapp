package com.disneyapp.disneyapp.dto;


import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Long id;

    private String image;

    private String title;

    private String creationDate;

    private double rating;

    private boolean deleted;

    private List<CharacterDTO> movieCharacter ;

    private List<GenreDTO> movieGenres;
}