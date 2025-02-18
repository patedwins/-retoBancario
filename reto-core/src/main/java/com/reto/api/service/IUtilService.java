package com.reto.api.service;

import com.reto.vo.response.security.GeneralResponseVo;
import org.springframework.http.HttpStatus;

/**
 * Definici&#243;n de servicios comunes
 *
 * @author patedwins
 */
public interface IUtilService {

    /**
     * Metodo para retornar el response general
     *
     * @param object
     * @param httpStatus
     * @param message
     * @return
     * @author patedwins
     */
    GeneralResponseVo asignarGeneralResponse(Object object, HttpStatus httpStatus, String message);
}
