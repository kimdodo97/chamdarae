package com.chamdarae.repository;

import com.chamdarae.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository<Recipe, Long> {
}
