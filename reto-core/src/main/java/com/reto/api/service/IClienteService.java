/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.postgres.entity.ClienteEntity;
import com.reto.vo.ClienteVo;
import com.reto.vo.response.security.GeneralResponseVo;

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
    GeneralResponseVo findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo saveNewCliente(ClienteVo data);

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo updateCliente(ClienteVo data);

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo deleteCliente(ClienteVo data);
}