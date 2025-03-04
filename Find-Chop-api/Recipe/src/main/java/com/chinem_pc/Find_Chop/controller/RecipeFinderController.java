package com.chinem_pc.Find_Chop.controller;

import com.chinem_pc.Find_Chop.dto.RequestRecipeDTO;
import com.chinem_pc.Find_Chop.dto.ResponseRecipesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



public interface RecipeFinderController {
    @GetMapping("")
    ResponseEntity<ResponseRecipesDTO> getRecipeByIngredients(@RequestBody RequestRecipeDTO request);
}
