package com.disneyapp.disneyapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="genres")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    //Attribute for soft deleted
    private boolean deleted = Boolean.FALSE;

    //Relation in the Movies
    @ManyToMany(mappedBy = "movieGenres", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovieEntity> genreMovies = new ArrayList<>();
}
