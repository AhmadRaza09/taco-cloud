package com.tacocloud.tacocloud.controller;

import com.tacocloud.tacocloud.domain.Ingredient;
import com.tacocloud.tacocloud.domain.Taco;
import com.tacocloud.tacocloud.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.tacocloud.tacocloud.domain.Ingredient.Type;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignFrom(Model model)
    {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findALL().forEach(i -> ingredients.add(i));

        Type [] types = Ingredient.Type.values();
        for (Type type : types)
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, Model model)
    {
        if(errors.hasErrors())
        {
            System.out.println("Error");
            model.addAttribute("design", new Taco());
            return "design";

        }
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
