package com.reto.api.service;

import com.reto.util.Constantes;
import com.reto.vo.response.security.GeneralResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementaci&#243;n de servicios comunes para el proceso de cumplimiento normativo.
 *
 * @author patedwins
 */
@Service
public class UtilService implements IUtilService {

    /**
     * Metodo para retornar el response general
     *
     * @param object
     * @param httpStatus
     * @param message
     * @return
     * @author patedwins
     */
    @Override
    public GeneralResponseVo asignarGeneralResponse(Object object, HttpStatus httpStatus, String message) {
        String status = Constantes.ERRONEO;
        if (httpStatus.value() == 200) {
            status = Constantes.EXITOSO;
        }
        return new GeneralResponseVo().toBuilder()
                .object(object)
                .code(httpStatus.value())
                .message(message)
                .httpStatus(httpStatus)
                .status(status)
                .build();
    }
}
