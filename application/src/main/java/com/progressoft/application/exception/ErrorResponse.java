package com.progressoft.application.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String url;
    private HttpStatus statusCode;
}
