package com.chamdarae.domain;

import javax.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Recipe {
    @Id @GeneratedValue
    @Column(name="recipe_id")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;

    private LocalDate createDate;
    private LocalDate modifiedDate;
    private String title;
    private String content;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
}
