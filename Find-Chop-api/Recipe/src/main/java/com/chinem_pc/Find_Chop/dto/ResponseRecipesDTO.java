package com.chinem_pc.Find_Chop.dto;

import java.util.List;

public record ResponseRecipesDTO(Integer recipesFound, List<RecipesDTO> recipes) {}
