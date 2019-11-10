package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PizzaRepository {

    private List<Pizza> pizzas = new ArrayList<Pizza>() {{
       add(new Pizza("Margherita", "(tomato sauce, mozzarella)"));
       add(new Pizza("Carbonara", "(fresh cream, mozzarella, bacon)"));
       add(new Pizza("Vegetariana", "(tomato sauce, mushrooms)"));
       add(new Pizza("Calzone", "(Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)"));
       add(new Pizza("Cheddar", "(cheddar, tomato sauce)"));
       add(new Pizza("Capricciosa", "(tomato sauce, cheese, ham)"));
       add(new Pizza("Burger Classic", "(barbecue sauce, beef, mozzarella, onions)"));
       add(new Pizza("Boston Barbecue", "(ham, chicken meat, onions)"));
       add(new Pizza("Vegie Vulcano", "(tomato sauce, mozzarella, sausage)"));
       add(new Pizza("Boston Barbecue", "(Taleggio, Mascarpone, Gorgonzola, Parmesan)"));
    }};

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }
}
