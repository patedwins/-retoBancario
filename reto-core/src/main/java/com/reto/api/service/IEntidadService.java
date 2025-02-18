/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.postgres.entity.EntidadEntity;
import com.reto.vo.request.security.EntidadNewRequestVo;
import com.reto.vo.request.security.EntidadRequestVo;
import com.reto.vo.response.security.GeneralResponseVo;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IEntidadService {

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    List<EntidadEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo saveNewEntidad(EntidadNewRequestVo data);

    /**
     * Update a cuenta
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo updateEntidad(EntidadRequestVo data);

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo deleteEntidad(EntidadRequestVo data);

    /**
     * Find all entidad
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo findAllEntidad();
}