package hu.nye.progkor.fruits.controller;

import hu.nye.progkor.fruits.model.Fruit;
import hu.nye.progkor.fruits.service.FruitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/update")
    public String updatePage() {
        return "update";
    }

    @GetMapping("/add-new")
    public String addPage() {
        return "addfruit";
    }

}
