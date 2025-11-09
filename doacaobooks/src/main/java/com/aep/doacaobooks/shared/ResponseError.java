package com.aep.doacaobooks.shared;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private LocalDateTime timestamp;
    private String status;
    private int statusCode;
    private String error;
    private String message;
    private String path;
    private String details;
}
