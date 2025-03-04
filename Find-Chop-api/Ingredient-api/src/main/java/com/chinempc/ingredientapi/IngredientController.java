package com.chinempc.ingredientapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingredients")
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService service;

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllIngredients() {
        return ResponseEntity.ok()
                .body(service.findAll());
    }

    @GetMapping("/name/{ingredientName}")
    public ResponseEntity<ResponseDTO> getIngredientByName(@PathVariable String ingredientName) {
        return ResponseEntity.ok()
                .body(service.findByName(ingredientName));
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ResponseDTO>> getIngredientsByCategory(@PathVariable String categoryName) {
        return ResponseEntity.ok()
                .body(service.findByCategory(categoryName));
    }

}
