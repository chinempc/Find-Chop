package com.chinem_pc.Find_Chop.mapper;

import com.chinem_pc.Find_Chop.dto.RecipesDTO;
import com.chinem_pc.Find_Chop.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeMapper {
    // DTO to Recipe

    // Recipe to DTO
    public List<RecipesDTO> multiToDTO(List<Recipe> recipes) {
        List<RecipesDTO> recipesDTOList = new ArrayList<>();

        for (Recipe recipe : recipes) {
            recipesDTOList.add(RecipesDTO.builder()
                    .recipe_name(recipe.getRecipeName())
                    .description(recipe.getDescription())
                    .url(recipe.getUrl())
                    .image(recipe.getImage())
                    .build());
        }
        return recipesDTOList;
    }

    public RecipesDTO singleToDTO(Recipe recipe) {
        return RecipesDTO.builder()
                .recipe_name(recipe.getRecipeName())
                .description(recipe.getDescription())
                .url(recipe.getUrl())
                .image(recipe.getImage())
                .build();
    }
}
