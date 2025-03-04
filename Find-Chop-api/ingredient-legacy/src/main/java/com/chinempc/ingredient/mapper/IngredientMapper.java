package com.chinempc.ingredient.mapper;

import com.chinempc.ingredient.dto.ResponseDTO;
import com.chinempc.ingredient.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientMapper {
    // DTO to POJO

    // POJO to DTO
    public List<ResponseDTO> multiToDTO(List<Ingredient> ingredients) {
        List<ResponseDTO> ingredientsDTOList = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientsDTOList.add(new ResponseDTO(
                    ingredient.getName(),
                    ingredient.getCategory()
            ));
        }
        return ingredientsDTOList;
    }

    public ResponseDTO singleToDTO(Ingredient ingredient) {
        return new ResponseDTO(
                ingredient.getName(),
                ingredient.getCategory()
        );
    }
}
