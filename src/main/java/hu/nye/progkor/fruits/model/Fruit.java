package hu.nye.progkor.fruits.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Fruit {

  private Long id;

  private String fruit;

  private String variety;

  private int quantity;

  private Boolean isOrganic;
}
