package com.chamdarae.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Ingredient {
    @Id
    @GeneratedValue
    @Column(name="ingredient_id")
    private Long id;

    private String name;

    private Long standardAmount;
    private Long energy;
    private Double protein;
    private Double fat;
    private Double carbon;
    private Double sugar;
    private Double salt;
    private Double saturatedFat;
    private Double weight;

    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
}
