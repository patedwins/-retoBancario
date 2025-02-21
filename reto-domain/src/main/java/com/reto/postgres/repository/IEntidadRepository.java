/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.postgres.repository;

import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IEntidadRepository extends JpaRepository<EntidadEntity, Integer> {

    /**
     * Permite obtener la entidad por nombre
     *
     * @param nombre
     * @return
     */
    List<PersonaEntity> findByNombre(String nombre);
}
