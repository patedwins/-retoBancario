package com.reto.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * JwtResponse.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Data
public class CuentaVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer idEntidad;
    private String numCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
}
