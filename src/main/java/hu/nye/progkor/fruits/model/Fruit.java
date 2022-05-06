package hu.nye.progkor.fruits.model;

import lombok.*;

import javax.persistence.Entity;

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
