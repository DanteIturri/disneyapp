package com.disneyapp.disneyapp.mapper;

import com.disneyapp.disneyapp.dto.GenreDTO;
import com.disneyapp.disneyapp.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {
 @Autowired
 MovieMapper movieMapper;
 //Convert DTO  Entity
    public GenreEntity genreDTO2Entity(GenreDTO dto){
        GenreEntity entity = new GenreEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        return entity;
    }

    public GenreDTO genreEntity2Dto(GenreEntity entity,boolean b){
        GenreDTO dto = new GenreDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        if(b){
            dto.setGenreMovies(movieMapper.EntityList2DTOList(entity.getGenreMovies(),false));
        }
        return dto;

    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> GenreList, boolean b) {
        List<GenreDTO> dtos = new ArrayList<>();
        for (GenreEntity entity : GenreList) {
            dtos.add(this.genreEntity2Dto(entity,false));
        }
        return dtos;
    }

}
