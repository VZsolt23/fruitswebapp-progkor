package hu.nye.progkor.fruits.service;

import java.util.List;

import hu.nye.progkor.fruits.model.Fruit;

public interface FruitService {
  List<Fruit> getAllFruits();

  Fruit getFruit(Long id);

  Fruit addFruit(Fruit fruit);

  Fruit updateFruit(Long id, Fruit fruitUpdate);

  void deleteFruit(Long id);
}
