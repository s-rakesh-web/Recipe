package com.example.recepie.controller;
import com.example.recepie.model.Recipe;
import com.example.recepie.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/import")
    public String importData() {
        recipeService.importRecipesFromMapJson();
        return "Import completed!";
    }
    @GetMapping("/title")
    public List<Recipe> getByTitle(@RequestParam String title){
        return recipeService.findByTitle(title);
    }

    @GetMapping("/top")
    public List<Recipe> getTopRecipe(@RequestParam(defaultValue = "5") int limit){
        return recipeService.getTopRecipe(limit);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe request) {
        return recipeService.createRecipe(request);
    }
}
