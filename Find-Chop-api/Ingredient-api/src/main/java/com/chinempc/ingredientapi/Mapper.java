package com.chinempc.ingredientapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {

    // POJO -> DTO
    public List<ResponseDTO> multiPojoToDTO(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(n -> new ResponseDTO(n.getName(), n.getCategory()))
                .collect(Collectors.toList());
    }

    public ResponseDTO singlePojoToDTO(Ingredient ingredient) {
        return new ResponseDTO(ingredient.getName(), ingredient.getCategory());
    }
}
