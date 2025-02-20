package com.reto.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * MovimientoVo.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Data
public class MovimientoRegistarVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idCuentaCliente;
    private String cliente;
    private String numCuenta;
    private String tipoCuenta;
    private BigDecimal valorMovimiento;
}
