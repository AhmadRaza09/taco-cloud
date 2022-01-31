package com.tacocloud.tacocloud.controller;

import com.tacocloud.tacocloud.domain.Ingredient;
import com.tacocloud.tacocloud.domain.Ingredient.Type;
import com.tacocloud.tacocloud.domain.Order;
import com.tacocloud.tacocloud.domain.Taco;
import com.tacocloud.tacocloud.repository.IngredientRepository;
import com.tacocloud.tacocloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }


    @ModelAttribute(name = "order")
    public Order order()
    {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco()
    {
        return new Taco();
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
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order)
    {
        if(errors.hasErrors())
        {
            System.out.println("Error");
            return "design";
        }
        log.info("Processing design: " + design);
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
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
