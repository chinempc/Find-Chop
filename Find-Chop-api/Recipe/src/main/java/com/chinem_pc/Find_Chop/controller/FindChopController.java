package com.chinem_pc.Find_Chop.controller;

import com.chinem_pc.Find_Chop.dto.RecipesDTO;
import com.chinem_pc.Find_Chop.dto.RequestRecipeDTO;
import com.chinem_pc.Find_Chop.service.FindChopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/find-chop")
@RequiredArgsConstructor
@Slf4j
public class FindChopController {
    private final FindChopService findChopService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<List<RecipesDTO>> getRecipeByIngredients(@RequestBody RequestRecipeDTO request) {
        log.info("Request: {}", request);
        List<String> filteredRequest = new ArrayList<String>();
        for (String ingredient : request.ingredients()) {
            if (!Objects.equals(ingredient, "")) {
                filteredRequest.add(ingredient);
            }
        }
        log.info("Request after filtering: {}", filteredRequest);

        return ResponseEntity.ok()
                .body(findChopService.getRecipesByIngredients(new RequestRecipeDTO(filteredRequest)));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/top-recipes")
    public ResponseEntity<List<RecipesDTO>> topRecipes() {
        return ResponseEntity.ok()
                .body(findChopService.getTopRecipes());
    }

}
