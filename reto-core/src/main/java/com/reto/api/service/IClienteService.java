/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.postgres.entity.ClienteEntity;
import com.reto.vo.ClienteVo;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IClienteService {

    /**
     * Find all group catalogs.
     *
     * @return a @{@link ClienteEntity} list.
     */
    List<ClienteEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    String saveNewCliente(ClienteVo data) throws RetoException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updateCliente(ClienteVo data) throws RetoException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deleteCliente(ClienteVo data) throws RetoException;
}