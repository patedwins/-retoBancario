package com.reto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * JwtResponse.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CuentaClienteVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idEntidad;
    private Integer idCliente;
    private String numCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
}
