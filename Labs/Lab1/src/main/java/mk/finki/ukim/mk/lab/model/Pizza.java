package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Pizzas")
public class Pizza {

    @Id
    private String name;
    private String description;

    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;

    private boolean veggie;
}
