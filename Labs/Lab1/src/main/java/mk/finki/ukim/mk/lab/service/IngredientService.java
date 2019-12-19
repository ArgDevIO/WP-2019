package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IngredientService {

    Ingredient createIngredient(String name, boolean spicy, String amount, boolean veggie);

    Ingredient editIngredient(String ingredientId, String name, Boolean spicy, String amount, Boolean veggie);

    void deleteIngredient(String ingredientId);

    Page<Ingredient> getIngredients(int page, int size);

    Ingredient getIngredient(String ingredientId);

    List<Ingredient> getBySpicy(boolean spicy);
}
