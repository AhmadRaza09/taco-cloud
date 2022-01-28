package com.tacocloud.tacocloud.repository;

import com.tacocloud.tacocloud.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findALL();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);

}
