package th.ac.ku.veggie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import th.ac.ku.veggie.model.Vegetable;
import th.ac.ku.veggie.service.VegetableService;
import th.ac.ku.veggie.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/vegetable")
public class VegetableController {

    @Autowired
    private VegetableService service;
    @Autowired
    private UserService userServices;

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Vegetable vegetable = service.getOneById(id);
        model.addAttribute("vegetable", vegetable);
        return "vegetable-edit";
    }

    @GetMapping("/anonymous")
    public String getVegAnonymous(Model model)
    {
        model.addAttribute("vegetables", service.getAll());
        return "veg-anonymous";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Vegetable vegetable, Model model) {
        service.update(vegetable);
        return "redirect:/vegetable";
    }

    @GetMapping
    public String getVegetables(Model model,Authentication authentication)
    {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("vegetables", service.getAll());
        return "vegetables";
    }

    @GetMapping("/add")
    public String getAddForm(){
        // return vegetable-add.html
        return "vegetable-add";
    }
    @PostMapping("/add")
    public String addVegetable(@ModelAttribute Vegetable vegetable, Model model) {
        // พอรับเข้ามาจะเอาเข้า List
        service.addVegetable(vegetable);

        return "redirect:/vegetable";
    }

}
