package com.chinempc.ingredient.service;

import com.chinempc.ingredient.dto.ResponseDTO;
import com.chinempc.ingredient.mapper.IngredientMapper;
import com.chinempc.ingredient.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    
    private final MongoTemplate mongoTemplate;
    private final IngredientMapper mapper;


    public List<ResponseDTO> findAll() {
        return  mapper.multiToDTO(mongoTemplate.findAll(Ingredient.class));
    }

    public ResponseDTO findByName(String ingredientName) {
        /*
            Query Database for all categories that match.
            - Map those to ResponseDTOs
            - Return List of ResponseDTOs
         */

        // Query MongoDB by name
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(ingredientName));

        return mapper.singleToDTO(mongoTemplate.find(query, Ingredient.class).get(0));
    }

    public List<ResponseDTO> findByCategory(String categoryName) {
        /*
            Query Database for all categories that match.
            - Map those to ResponseDTOs
            - Return List of ResponseDTOs
         */

        // Query MongoDB by category
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(categoryName));

        return mapper.multiToDTO(mongoTemplate.find(query, Ingredient.class));
    }
}
