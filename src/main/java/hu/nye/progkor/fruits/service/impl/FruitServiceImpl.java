package hu.nye.progkor.fruits.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.nye.progkor.fruits.model.Fruit;
import hu.nye.progkor.fruits.model.exception.NotFoundException;
import hu.nye.progkor.fruits.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService {

  private final List<Fruit> dataBase = new ArrayList<>();

  @Autowired
  public FruitServiceImpl() {
    dataBase.add(new Fruit(1L, "Apple", "Golden delicious", 25, false));
    dataBase.add(new Fruit(2L, "Pear", "Asian pear", 39, true));
    dataBase.add(new Fruit(3L, "Plum", "Damson plum", 10, false));
  }

  public FruitServiceImpl(final List<Fruit> fruits) {
    dataBase.addAll(fruits);
  }

  @Override
  public List<Fruit> getAllFruits() {
    return Collections.unmodifiableList(dataBase);
  }

  @Override
  public Fruit getFruit(Long id) {
    return dataBase.stream()
        .filter(fruit -> fruit.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("Not found this fruit with ID " + id));
  }

  @Override
  public Fruit addFruit(Fruit fruit) {
    fruit.setId(getNewId());
    if (fruit.getIsOrganic() == null) {
      fruit.setIsOrganic(false);
    }

    boolean isExisting = false;
    Fruit[] list = getAllFruits().toArray(new Fruit[0]);
    Fruit existingFruitUpdate;
    for (Fruit value : list) {
      if (fruit.getFruit().equals(value.getFruit())
          && fruit.getVariety().equals(value.getVariety())
          && fruit.getIsOrganic().equals(value.getIsOrganic())) {
        isExisting = true;
        existingFruitUpdate = fruit;
        existingFruitUpdate.setQuantity(fruit.getQuantity() + value.getQuantity());
        updateFruit(value.getId(), existingFruitUpdate);
        break;
      }
    }

    if (!isExisting) {
      dataBase.add(fruit);
    }

    return fruit;
  }

  @Override
  public Fruit updateFruit(Long id, Fruit fruitUpdate) {
    final Fruit fruit = getFruit(id);
    fruit.setFruit(fruitUpdate.getFruit());
    fruit.setVariety(fruitUpdate.getVariety());
    fruit.setQuantity(fruitUpdate.getQuantity());
    if (fruitUpdate.getIsOrganic() != null) {
      fruit.setIsOrganic(fruitUpdate.getIsOrganic());
    } else {
      fruit.setIsOrganic(false);
    }

    return fruit;
  }

  @Override
  public void deleteFruit(Long id) {
    final Fruit fruit = getFruit(id);
    dataBase.remove(fruit);
  }

  private long getNewId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return dataBase.stream()
        .mapToLong(Fruit::getId)
        .max()
        .orElse(0);
  }
}
