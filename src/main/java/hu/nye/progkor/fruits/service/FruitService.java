package hu.nye.progkor.fruits.service;

import hu.nye.progkor.fruits.model.Fruit;

import java.util.List;

public interface FruitService {
    List<Fruit> getAllFruits();

    Fruit getFruit(Long id);

    Fruit addFruit(Fruit fruit);

    Fruit updateFruit(Long id, Fruit fruitUpdate);

    void deleteFruit(Long id);
}
