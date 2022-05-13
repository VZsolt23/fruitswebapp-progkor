package hu.nye.progkor.fruits.controller;

import java.util.List;

import hu.nye.progkor.fruits.model.Fruit;
import hu.nye.progkor.fruits.service.FruitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fruit")
public class FruitRestController {
  private final FruitService fruitService;

  public FruitRestController(FruitService fruitService) {
    this.fruitService = fruitService;
  }

  @GetMapping
  public List<Fruit> getAllFruit() {
    return fruitService.getAllFruits();
  }

  @GetMapping("/{id}")
  Fruit getFruit(final @PathVariable("id") Long id) {
    return fruitService.getFruit(id);
  }

  @PostMapping
  Fruit addFruit(final @RequestBody Fruit fruit) {
    return fruitService.addFruit(fruit);
  }

  @PutMapping("/{id}")
  Fruit updateFruit(final @PathVariable Long id, final @RequestBody Fruit fruitChange) {
    return fruitService.updateFruit(id, fruitChange);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteFruit(final @PathVariable long id) {
    fruitService.deleteFruit(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
