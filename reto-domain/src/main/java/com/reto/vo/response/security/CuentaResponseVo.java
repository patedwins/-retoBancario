package com.reto.vo.response.security;

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
public class CuentaResponseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombreCuenta;
    private EntidadResponseVo entidad;
    private String numCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private BigDecimal saldoDisponible;
    private Boolean estado;
}
