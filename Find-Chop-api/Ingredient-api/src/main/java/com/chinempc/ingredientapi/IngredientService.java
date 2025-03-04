package com.chinempc.ingredientapi;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final MongoTemplate mongoTemplate;
    private final Mapper mapper;


    public List<ResponseDTO> findAll() {
        return mapper.multiPojoToDTO(mongoTemplate.findAll(Ingredient.class));
    }

    public ResponseDTO findByName(String ingredientName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(ingredientName));

        return mapper.singlePojoToDTO(Objects.requireNonNull(mongoTemplate.findOne(query, Ingredient.class)));
    }

    public List<ResponseDTO> findByCategory(String categoryName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(categoryName));

        return mapper.multiPojoToDTO(mongoTemplate.find(query, Ingredient.class));
    }
}
