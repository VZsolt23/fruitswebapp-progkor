package hu.nye.progkor.fruits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FruitController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
    @GetMapping("/listoffruits")
    public String listOfFruitsPage() {
        return "listoffruits";
    }

    @GetMapping("/update")
    public String updatePage() {
        return "update";
    }

    @GetMapping("/addfruit")
    public String addPage() {
        return "addfruit";
    }
}
