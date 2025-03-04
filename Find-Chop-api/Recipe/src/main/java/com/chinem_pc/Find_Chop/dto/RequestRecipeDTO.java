package com.chinem_pc.Find_Chop.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestRecipeDTO(
        @NotNull(message = "Must pass in at least one ingredient")
        List<String> ingredients
) {}
