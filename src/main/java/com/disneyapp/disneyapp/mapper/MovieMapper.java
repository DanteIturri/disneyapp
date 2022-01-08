package com.disneyapp.disneyapp.mapper;


import com.disneyapp.disneyapp.dto.MovieBasicDTO;
import com.disneyapp.disneyapp.dto.MovieDTO;
import com.disneyapp.disneyapp.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {
    @Autowired
    CharacterMapper characterMapper;
    @Autowired
    GenreMapper genreMapper;

    public MovieEntity MovieDTO2Entity(MovieDTO dto){
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setRating(dto.getRating());
        entity.setCreationDate(this.String2LocalDate(dto.getCreationDate()));
        return entity;
    }

    public MovieDTO  MovieEntity2DTO(MovieEntity entity, boolean b){
        MovieDTO dto = new MovieDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setRating(entity.getRating());
        dto.setCreationDate(this.LocalDateToString(entity.getCreationDate()));
        if(b){
            dto.setMovieCharacter(this.characterMapper.characterEntityList2DTOList(entity.getMovieCharacters(),false));
            dto.setMovieGenres(this.genreMapper.genreEntityList2DTOList(entity.getMovieGenres(),false));
        }
        return dto;
    }

    public List<MovieDTO> EntityList2DTOList(List<MovieEntity> movieList, boolean b){
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity: movieList){
            dtos.add(this.MovieEntity2DTO(entity,b));
        }
        return dtos;
    }
    //Convert Entity => BasicDTO
    public MovieBasicDTO MovieEntity2BasicDTO(MovieEntity entity){
        MovieBasicDTO dto = new MovieBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(this.LocalDateToString(entity.getCreationDate()));
        return dto;
    }

    //Convert EntityList => BasicDTOList
    public List<MovieBasicDTO> MovieEntityList2BasicDTOList(List<MovieEntity> entities){
        List<MovieBasicDTO> dtos = new ArrayList<>();
        for (MovieEntity ent: entities){
            dtos.add(this.MovieEntity2BasicDTO(ent));
        }
        return dtos;
    }
    //Utils
    public LocalDate String2LocalDate(String enteredDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate transformedDate = LocalDate.parse(enteredDate, formatter);
        return transformedDate;
    }

    public String LocalDateToString(LocalDate creationDate) {
        String formattedDate = creationDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return formattedDate;
    }


}
