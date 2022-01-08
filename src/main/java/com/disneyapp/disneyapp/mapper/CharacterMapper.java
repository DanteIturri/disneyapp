package com.disneyapp.disneyapp.mapper;

import com.disneyapp.disneyapp.dto.CharacterBasicDTO;
import com.disneyapp.disneyapp.dto.CharacterDTO;
import com.disneyapp.disneyapp.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean b){
        CharacterDTO dto = new CharacterDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if (b){
            dto.setCharacterMovies(movieMapper.EntityList2DTOList(entity.getCharacterMovies(),false));
        }
        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean b){
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity: entities){
            dtos.add(this.characterEntity2DTO(entity,b));
        }
        return dtos;
    }
    //Convert Entity => BasicDTO
    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity){
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;
    }

    //Convert EntityList => BasicDTOList
    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities){
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        for (CharacterEntity ent: entities){
            dtos.add(this.characterEntity2BasicDTO(ent));
        }
        return dtos;
    }


}
