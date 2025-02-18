/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.vo.MovimientoFechasVo;
import com.reto.vo.MovimientoRegistarVo;
import com.reto.vo.MovimientoVo;
import com.reto.vo.response.security.GeneralResponseVo;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IMovimientoService {

    /**
     * Find all movimientos por entidad
     *
     * @return a @{@link MovimientoVo} list.
     */
    GeneralResponseVo obtenerMovimientoPorEntidad(Integer idEntidad);

    /**
     * Find all movimientos por entidad
     *
     * @return a @{@link MovimientoVo} list.
     */
    GeneralResponseVo generarMovimientoPorEntidad(MovimientoRegistarVo movimiento);

    /**
     * Find all movimientos por entidad
     *
     * @return a @{@link MovimientoVo} list.
     */
    GeneralResponseVo obtenerMovimientoPorFecha(String fecDesde, String fecHasta) throws RetoException;
}