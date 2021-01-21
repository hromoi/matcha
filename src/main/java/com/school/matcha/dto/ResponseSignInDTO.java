package com.school.matcha.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseSignInDTO {
    private String token;
}
