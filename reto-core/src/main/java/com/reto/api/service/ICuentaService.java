/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.postgres.entity.CuentaEntity;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.vo.CuentaClienteVo;
import com.reto.vo.CuentaVo;

import java.util.List;

/**
 * Cuenta service interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface ICuentaService {

    /**
     * Find all cuenta.
     *
     * @return a @{@link EntidadEntity} list.
     */
    List<CuentaEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    String saveNewCuenta(CuentaVo data) throws RetoException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updateCuenta(CuentaVo data) throws RetoException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deleteCuenta(CuentaVo data) throws RetoException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String crearCuentaCliente(CuentaClienteVo data) throws RetoException;
}