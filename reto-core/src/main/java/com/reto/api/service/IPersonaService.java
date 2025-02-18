/*
 * Copyright (c) 2025
 *
 *
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.entity.PersonaEntity;
import com.reto.vo.request.security.PersonaNewRequestVo;
import com.reto.vo.request.security.PersonaRequestVo;
import com.reto.vo.response.security.GeneralResponseVo;

import java.util.List;

/**
 * Base catálogo service interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IPersonaService {

    /**
     * Find all person.
     *
     * @return a @{@link EntidadEntity} list.
     */
    List<PersonaEntity> findAll();

    /**
     * Save new persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo saveNewPerson(PersonaNewRequestVo person);

    /**
     * Update a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo updatePerson(PersonaRequestVo person);

    /**
     * Delete a persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo deletePerson(PersonaRequestVo person);

    /**
     * Find All  persona
     *
     * @return a @{@link String} list.
     */
    GeneralResponseVo findAllPerson();
}