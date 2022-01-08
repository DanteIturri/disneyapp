package com.disneyapp.disneyapp.service.impl;

import com.disneyapp.disneyapp.dto.GenreDTO;
import com.disneyapp.disneyapp.entity.GenreEntity;
import com.disneyapp.disneyapp.exception.ParamNotFound;
import com.disneyapp.disneyapp.mapper.GenreMapper;
import com.disneyapp.disneyapp.repository.GenreRepository;
import com.disneyapp.disneyapp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreMapper genreMapper;

    @Autowired
    GenreRepository genreRepository;


    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity savedEntity = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2Dto(savedEntity, false);
        return result;
    }


    public List<GenreDTO> getAllGenre() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = this.genreMapper.genreEntityList2DTOList(entities, false);
        return result;
    }


    public void deletedGenreByid(Long id) {
        genreRepository.deleteById(id);
    }


    public GenreDTO editGenreById(Long id, GenreDTO genreToEdit) {
        GenreEntity savedGenre = this.handleFindById(id);
        savedGenre.setImage(genreToEdit.getImage());
        savedGenre.setName(genreToEdit.getName());
        GenreEntity editedGenre = genreRepository.save(savedGenre);
        GenreDTO resultDTO = genreMapper.genreEntity2Dto(editedGenre, false);
        return resultDTO;
    }

    public GenreEntity handleFindById(Long id) {
        Optional<GenreEntity> toBeFound = genreRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Genre for id: " + id);
        }
        return toBeFound.get();
    }
}
