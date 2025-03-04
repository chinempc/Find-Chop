package com.chinem_pc.Find_Chop.service;

import com.chinem_pc.Find_Chop.dto.RecipesDTO;
import com.chinem_pc.Find_Chop.dto.RequestRecipeDTO;
import com.chinem_pc.Find_Chop.dto.ResponseRecipesDTO;
import com.chinem_pc.Find_Chop.mapper.RecipeMapper;
import com.chinem_pc.Find_Chop.model.Ingredient;
import com.chinem_pc.Find_Chop.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FindChopService {

    private final RecipeMapper mapper;
    private final MongoTemplate mongoTemplate;
    private final RedisService redisService;
    public List<RecipesDTO> getRecipesByIngredients(RequestRecipeDTO request) {
        // I am assuming the user cannot add ingridents that do not exist in mongo,
        // thus it is redundant to check if the ingr exists in mongo before continuing.

        /*
        Query:
            - Get all recipes that have as many or less than the length of the request ingrs
            - From that list of recipes check if the list of request ingrs contains all the ingrs
                in each recipe
            -

        BasicQuery query = new BasicQuery("{" +
                "   $expr: {" +
                "      $lte: [{" +
                "         $size: '$ingredients'" + // Check if recipe has the same or fewer ingredients than userList
                "      }, " + request.ingredients().size() + "]" +
                "   }," +
                "   ingredients: {" +
                "      $all: " + request.ingredients().toString() +  // Ensure all recipe ingredients are in the userList
                "   }" +
                "}");

         */
        // Testing ["beef", "salt", "tomato", "onion", "potato", "curry powder"]
        // If a match is found then that should be added to the list, else removed

        // Step 1: Get all recipes that have up to the same number of ingredients as the userIngredients list
        Criteria sizeCriteria = Criteria.where("$expr")
                .is(new Document("$lte", Arrays.asList(new Document("$size", "$ingredients"), request.ingredients().size())));

        // Build the initial query
        Query query = new Query(sizeCriteria);

        // Execute the query and get the initial list of recipes
        List<Recipe> initialRecipes = mongoTemplate.find(query, Recipe.class);
        //List<RecipesDTO> initialRecipes = mapper.multiToDTO(mongoTemplate.find(query, Recipe.class));

        // Step 2: Filter recipes based on ingredient names matching the requested keywords
        List<RecipesDTO> filteredRecipes = initialRecipes.stream()
                .filter(recipe -> allIngredientsMatch(recipe, request.ingredients()))
                .map(mapper::singleToDTO)
                .collect(Collectors.toList());

        redisService.incrementCounter();
        return filteredRecipes;
    }

    private boolean allIngredientsMatch(Recipe initialRecipes, List<String> requestedIngredients) {

        // Check if all requested ingredients are matched in the recipe's ingredient names
        for (Ingredient ingredient : initialRecipes.getIngredients()) {
            String ingredientName = ingredient.getName();
            if (ingredientName != null) {
                boolean matchFlag = false;
                for (String requestedIngredient : requestedIngredients){
                    if (ingredientName.toLowerCase().contains(requestedIngredient.toLowerCase())){
                        matchFlag = true;
                        break;
                    }
                }
                // I may be returning too soon here
                if (!matchFlag){
                    return matchFlag;
                }
            }
        }

        return true;
    }

    public List<RecipesDTO> getTopRecipes() {
        /*
            * Make a call to consume from the top-recipes topic in RabbitMQ
            * Query the database for all 3 names
                - Convert those 3 into DTO
            * Return DTOs back to frontend
         */

        /*
            * Recipe API has access to the Top Recipes in its db under the collection top_recipes
            * This function return the 3 objects in that collection (There will always be only 3)
            * When this function and getRecipeByIngredients is called
                - Make a call to Redis to update counter, when 100 reset counter and send message to queue
            *
         */

        Query query = new Query().limit(3);
        redisService.incrementCounter();
        return mapper.multiToDTO(mongoTemplate.find(query, Recipe.class));
    }
}
