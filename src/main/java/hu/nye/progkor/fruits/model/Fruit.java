package hu.nye.progkor.fruits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Fruit {

    private Long ID;

    private String fruit;

    private String variety;

    private int quantity;

    private Boolean isOrganic;
}
