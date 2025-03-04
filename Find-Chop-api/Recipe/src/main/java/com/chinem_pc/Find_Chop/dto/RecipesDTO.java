package com.chinem_pc.Find_Chop.dto;

import com.chinem_pc.Find_Chop.model.Ingredient;
import com.chinem_pc.Find_Chop.model.Nutrition;
import com.chinem_pc.Find_Chop.model.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipesDTO {
    private String recipe_name;
    private String description;
    private String url;
    private String image;
}
