package com.chinem_pc.Find_Chop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Nutrition")
public class Nutrition {
    private String calories;
    private String fat;
    private String carbs;
    private String protein;
}
