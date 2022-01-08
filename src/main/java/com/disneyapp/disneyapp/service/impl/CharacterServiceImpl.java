package com.disneyapp.disneyapp.service.impl;

import com.disneyapp.disneyapp.dto.CharacterDTO;
import com.disneyapp.disneyapp.dto.CharacterFiltersDTO;
import com.disneyapp.disneyapp.entity.CharacterEntity;
import com.disneyapp.disneyapp.exception.ParamNotFound;
import com.disneyapp.disneyapp.mapper.CharacterMapper;
import com.disneyapp.disneyapp.repository.CharacterRepository;
import com.disneyapp.disneyapp.repository.specifications.CharacterSpecification;
import com.disneyapp.disneyapp.service.CharacterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterSevice {
    @Autowired
    CharacterMapper characterMapper;
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterSpecification characterSpec;


    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity saveEntity = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(saveEntity,false);
        return result;
    }

    public List<CharacterDTO> getAllCharacter(){
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities,false);
        return result;
    }

    @Override
    public CharacterDTO getCharDetails(Long id) {
        CharacterEntity dbChar = this.handleFindById(id);
        CharacterDTO resultDTO = characterMapper.characterEntity2DTO(dbChar,true);
        return resultDTO;
    }

    public CharacterDTO editCharacterById(Long id, CharacterDTO charToEdit) {
        CharacterEntity savedChar = this.handleFindById(id);
        savedChar.setImage(charToEdit.getImage());
        savedChar.setName(charToEdit.getName());
        savedChar.setAge(charToEdit.getAge());
        savedChar.setWeight(charToEdit.getWeight());
        savedChar.setHistory(charToEdit.getHistory());
        CharacterEntity editedChar = characterRepository.save(savedChar);
        CharacterDTO resultDTO = characterMapper.characterEntity2DTO(editedChar, false);
        return resultDTO;
    }

    public void deleteCharacterById(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
        List<CharacterEntity> entityList = characterRepository.findAll(characterSpec.getFiltered(filtersDTO));
        List<CharacterDTO> resultDTO = characterMapper.characterEntityList2DTOList(entityList, true);
        return resultDTO;
    }

    // --- ERROR HANDLING ---
    public CharacterEntity handleFindById(Long id) {
        Optional<CharacterEntity> toBeFound = characterRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Character for id: " + id);
        }
        return toBeFound.get();
    }
}
