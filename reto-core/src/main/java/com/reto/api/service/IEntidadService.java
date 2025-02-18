/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.postgres.entity.EntidadEntity;

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
    String saveNewEntidad(EntidadEntity data) throws RetoException;

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    String updateEntidad(EntidadEntity data) throws RetoException;

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    String deleteEntidad(EntidadEntity data) throws RetoException;
}