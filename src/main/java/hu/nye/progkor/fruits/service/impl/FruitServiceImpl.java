package hu.nye.progkor.fruits.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.nye.progkor.fruits.model.Fruit;
import hu.nye.progkor.fruits.model.exception.NotFoundException;
import hu.nye.progkor.fruits.service.FruitService;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService {

    private static final List<Fruit> DATA_BASE = new ArrayList<>();

    static {
        DATA_BASE.add(new Fruit(1L, "Apple", "Golden delicious", 25, false));
        DATA_BASE.add(new Fruit(2L, "Pear", "Asian pear", 39, true));
        DATA_BASE.add(new Fruit(3L, "Plum", "Damson Plum", 10, false));
    }

    @Override
    public List<Fruit> getAllFruits() {
        return Collections.unmodifiableList(DATA_BASE);
    }

    @Override
    public Fruit getFruit(Long id) {
        return DATA_BASE.stream()
                .filter(fruit -> fruit.getID().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found this fruit with ID " + id));
    }

    @Override
    public Fruit addFruit(Fruit fruit) {
        fruit.setID(getNewId());
        if (fruit.getIsOrganic() == null) {
            fruit.setIsOrganic(false);
        }

        boolean isExisting = false;
        Fruit[] list = getAllFruits().toArray(new Fruit[0]);
        Fruit existingFruitUpdate;
        for (Fruit value : list) {
            if (fruit.equals(value)) {
                isExisting = true;
                existingFruitUpdate = fruit;
                existingFruitUpdate.setQuantity(fruit.getQuantity() + value.getQuantity());
                updateFruit(value.getID(), existingFruitUpdate);
                break;
            }
        }

        if (!isExisting) {
            DATA_BASE.add(fruit);
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
        DATA_BASE.remove(fruit);
    }

    private long getNewId() {
        return getLastId() + 1L;
    }

    private long getLastId() {
        return DATA_BASE.stream()
                .mapToLong(Fruit::getID)
                .max()
                .orElse(0);
    }
}
