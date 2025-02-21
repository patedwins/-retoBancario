package com.reto.vo.response.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * General Response to mapping external services
 *
 * @author patedwins
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseVo {
    private String message;
    private String status;
    private Integer code;
    private Object object;
    private HttpStatus httpStatus;
}

