package com.disneyapp.disneyapp.controller;

import com.disneyapp.disneyapp.dto.GenreDTO;
import com.disneyapp.disneyapp.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    GenreService genreService;
    //All Genre
    @GetMapping("/all")
    public ResponseEntity<List<GenreDTO>> getAll(){
        List<GenreDTO> genres = genreService.getAllGenre();
        return ResponseEntity.ok().body(genres);
    }
    //Save Genre
    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto){
        GenreDTO genreSave = genreService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSave);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> editGenre(@PathVariable Long id, @RequestBody GenreDTO genreToEdit){
        GenreDTO editedGenre = genreService.editGenreById(id, genreToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenre);
    }

    // --- DELETE ---
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        genreService.deletedGenreByid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
