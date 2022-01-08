package com.disneyapp.disneyapp.dto;

import com.disneyapp.disneyapp.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

    private boolean deleted;

    private List<MovieDTO> CharacterMovies;
}
