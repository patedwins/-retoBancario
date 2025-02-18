/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.postgres.repository;

import com.reto.postgres.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    /**
     * Permite obtener la persona por identificacion
     *
     * @param identificacion
     * @return
     */
    List<PersonaEntity> findByIdentificacion(String identificacion);
}
