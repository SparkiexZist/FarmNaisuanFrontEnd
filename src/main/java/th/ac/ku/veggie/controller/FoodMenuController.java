package th.ac.ku.veggie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodMenuController {
    @RequestMapping("/menu")
    public String getHomePage(Model model) {
        return "food-menu";
    }
}