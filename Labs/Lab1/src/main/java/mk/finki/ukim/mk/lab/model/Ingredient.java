package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient {

    @Id
    private String name;
    private boolean spicy;
    private String amount;
    private boolean veggie;
}
