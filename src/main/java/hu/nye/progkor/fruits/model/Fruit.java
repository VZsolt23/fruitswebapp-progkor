package hu.nye.progkor.fruits.model;

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
public class Fruit {

  private Long id;

  private String crop;

  private String variety;

  private int quantity;

  private Boolean isOrganic;
}
