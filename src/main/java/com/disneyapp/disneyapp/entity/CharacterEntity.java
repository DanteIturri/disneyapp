package com.disneyapp.disneyapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

    // Soft Delete:
    private boolean deleted = Boolean.FALSE;

    // ManyToMany: Movies
    @ManyToMany(mappedBy = "movieCharacters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovieEntity> characterMovies = new ArrayList<>();
}
