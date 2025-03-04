package com.chinempc.findchopapiclickcounter.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class RequestDTO {
    private String recipe_name;
}
