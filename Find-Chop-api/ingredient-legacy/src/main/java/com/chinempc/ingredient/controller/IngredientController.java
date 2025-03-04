package com.chinempc.ingredient.controller;

import com.chinempc.ingredient.dto.ResponseDTO;
import com.chinempc.ingredient.model.Ingredient;
import com.chinempc.ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingredient")
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService service;

    @GetMapping("")
    public ResponseEntity<List<ResponseDTO>> getAllIngredients() {
        return ResponseEntity.ok()
                .body(service.findAll());
    }

    @GetMapping("/name/{ingredientName}")
    public ResponseEntity<ResponseDTO> getIngredientByName(@PathVariable String ingredientName) {
        return ResponseEntity.ok()
                .body(service.findByName(ingredientName));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ResponseDTO>> getIngredientsCategory(@PathVariable(name = "category") String category){
        return ResponseEntity.ok()
                .body(service.findByCategory(category));
    }

}
