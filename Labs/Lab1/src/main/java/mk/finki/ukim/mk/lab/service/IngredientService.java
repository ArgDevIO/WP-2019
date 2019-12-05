package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;

public interface IngredientService {

    Ingredient createIngredient(String name, boolean spicy, float amount, boolean veggie);

    Ingredient editIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie);
}
