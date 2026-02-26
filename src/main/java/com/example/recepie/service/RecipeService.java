package com.example.recepie.service;

import com.example.recepie.model.Recipe;
import com.example.recepie.repository.RecipeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service

public class RecipeService{
    @Autowired
    private  RecipeRepository recipeRepository;

    public void importRecipesFromMapJson(){
        try{
            ObjectMapper mapper=new ObjectMapper();
            InputStream inputStream=new ClassPathResource("data.json").getInputStream();
            Map<String, Recipe> recipeMap=mapper.readValue(inputStream, new TypeReference<Map<String, Recipe>>() {
            });

            recipeRepository.saveAll(recipeMap.values());
            System.out.println("Import completed successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Recipe> findByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    public Recipe createRecipe(Recipe rec) {
        if (rec.getTitle() == null) {
            throw new IllegalArgumentException("title is has to be provided");
        }
        if (rec.getCuisine() == null) {
            throw new IllegalArgumentException("cuisine is has to be provided");
        }
        if (rec.getPrepTime() == null) {
            throw new IllegalArgumentException("Preparation time has to be provided");
        }
       if(rec.getCookTime()==null){
           throw new IllegalArgumentException("Cooking time has to be provided");
       }
        rec.setId(new ObjectId().toHexString());
        rec.setTitle(rec.getTitle());
        rec.setCuisine(rec.getCuisine());
        rec.setTotalTime(rec.getPrepTime() + rec.getCookTime());

        return recipeRepository.save(rec);
    }


    public List<Recipe> getTopRecipe(int limit){
        return recipeRepository.findByRatingNotNullOrderByRatingDesc(PageRequest.of(0,limit));
    }
}
