package com.disneyapp.disneyapp.service;

import com.disneyapp.disneyapp.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterSevice  {

    CharacterDTO save(CharacterDTO dto);

    List<CharacterDTO> getAllCharacter();
    CharacterDTO getCharDetails(Long id);

    CharacterDTO editCharacterById(Long id, CharacterDTO charToEdit);

    void deleteCharacterById(Long id);

    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);
}
