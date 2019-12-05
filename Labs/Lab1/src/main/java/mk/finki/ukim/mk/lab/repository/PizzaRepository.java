package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, String> {

    List<Pizza> findPizzaByIngredientsContains(Ingredient ingredient);

    @Query("FROM Pizza where size(ingredients) <= ?1")
    List<Pizza> findByIngredientsCount(int totalIngredients);
}
