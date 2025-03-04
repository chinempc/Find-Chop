package com.chinempc.findchopapiclickcounter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "click_count")
public class Click {
    @Id
    private String id;
    @Field("recipe_name")
    private String recipeName;
    @Field("click_count")
    private Integer clickCount;
}
