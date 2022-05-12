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

    @EqualsAndHashCode.Exclude private Long ID;

    private String fruit;

    private String variety;

    @EqualsAndHashCode.Exclude private int quantity;

    private Boolean isOrganic;
}
