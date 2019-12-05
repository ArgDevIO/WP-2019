package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> listPizzas();

    Pizza createPizza(String name, String description, List<String> ingredients, boolean veggie);

    Pizza editPizza(String id, String name, String description, List<String> ingredients, boolean veggie);

    void deletePizza(String id);

    List<Pizza> getAllPizzas(int totalIngredients);

    Pizza getPizza(String id);

    List<Pizza> getAllPizzasWithIngredient(String id);

    List<Ingredient> getCommonIngredients(String pizza1, String pizza2);
}

