/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.postgres.repository;

import com.reto.postgres.entity.EntidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IEntidadRepository extends JpaRepository<EntidadEntity, Integer> {

}
