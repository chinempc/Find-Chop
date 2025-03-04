package com.chinem_pc.Find_Chop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "recipes")
public class Recipe {
    @Field("recipe_name")
    private String recipeName; // Changed to match MongoDB field name

    private String author;
    private String description;
    private int servings;

    @Field("prep_time") // Specify the MongoDB field name
    private String prepTime;

    @Field("cook_time") // Specify the MongoDB field name
    private String cookTime;

    @Field("marinating_time") // Specify the MongoDB field name
    private String marinatingTime;

    @Field("total_time") // Specify the MongoDB field name
    private String totalTime;

    private String url;

    private List<String> categories;

    private List<Ingredient> ingredients;

    private List<Step> steps;

    private String image;

    @Field("special_equipment") // Specify the MongoDB field name
    private String specialEquipment;

    private Nutrition nutrition;
}
