/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.vo.CuentaClienteVo;
import com.reto.vo.CuentaVo;
import com.reto.vo.response.security.GeneralResponseVo;

/**
 * Cuenta service interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface ICuentaService {

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo saveNewCuenta(CuentaVo data);

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo updateCuenta(CuentaVo data);

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo deleteCuenta(CuentaVo data);

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo crearCuentaCliente(CuentaClienteVo data);

    /**
     * Find all cuenta
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo findAllCuenta();
}