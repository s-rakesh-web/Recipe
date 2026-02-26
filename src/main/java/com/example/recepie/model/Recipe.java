package com.example.recepie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    @JsonProperty("Contient")
    @Field("Contient")
    private String continent;

    @JsonProperty("Country_State")
    @Field("Country_State")
    private String countryState;

    private String cuisine;
    private String title;

    @JsonProperty("URL")
    @Field("URL")
    private String url;

    private Double rating;

    @JsonProperty("total_time")
    @Field("total_time")
    private Integer totalTime;

    @JsonProperty("prep_time")
    @Field("prep_time")
    private Integer prepTime;

    @JsonProperty("cook_time")
    @Field("cook_time")
    private Integer cookTime;

    private String description;
    private List<String> instructions;
    private Nutrients nutrients;
    private String serves;
}
