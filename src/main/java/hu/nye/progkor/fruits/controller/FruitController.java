package hu.nye.progkor.fruits.controller;

import java.util.List;

import hu.nye.progkor.fruits.model.Fruit;
import hu.nye.progkor.fruits.model.exception.NotFoundException;
import hu.nye.progkor.fruits.service.FruitService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fruit")
public class FruitController {

  private final FruitService fruitService;

  public FruitController(FruitService fruitService) {
    this.fruitService = fruitService;
  }

  @GetMapping
  public String homePage() {
    return "index";
  }

  @GetMapping("/list")
  public String getAllFruit(final Model model) {
    final List<Fruit> fruits = fruitService.getAllFruits();
    model.addAttribute("fruits", fruits);
    return "listoffruits";
  }

  @GetMapping("/update/{id}")
  public String updateFruit(final Model model, final @PathVariable Long id) {
    final Fruit fruit = fruitService.getFruit(id);
    model.addAttribute("fruit", fruit);
    return "update";
  }

  @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateFruitConfirm(final Model model, final Fruit fruitUpdate,
                                   final @RequestParam(value = "id", required = false) Long id) {
    final Fruit fruit = fruitService.updateFruit(id, fruitUpdate);
    model.addAttribute("fruit", fruit);
    return "redirect:/fruit/list";
  }

  @GetMapping("/list/{id}/delete")
  public String deleteFruit(final @PathVariable("id") Long id, final Model model) {
    try {
      fruitService.deleteFruit(id);
    } catch (NotFoundException e) {
      //empty
    }
    final List<Fruit> fruits = fruitService.getAllFruits();
    model.addAttribute("fruits", fruits);
    return "listoffruits";
  }

  @GetMapping("/add-new")
  public String addFruitPage(final Model model) {
    Fruit newFruit = new Fruit();
    model.addAttribute("newFruit", newFruit);
    return "addfruit";
  }

  @PostMapping("/list")
  public String addFruit(@ModelAttribute("newFruit") Fruit fruit) {
    fruitService.addFruit(fruit);
    return "redirect:/fruit/list";
  }

  @GetMapping("/error")
  public String errorPage() {
    return "error";
  }
}
