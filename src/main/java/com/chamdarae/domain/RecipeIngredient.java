package com.chamdarae.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class RecipeIngredient {
    @Id @GeneratedValue
    @Column(name="recipe_ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    private Long foodWeight;
}
