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
    @Id
    @GeneratedValue
    Long ID;

    String fruit;

    String variety;

    String quantity;

    Boolean isOrganic;
}
