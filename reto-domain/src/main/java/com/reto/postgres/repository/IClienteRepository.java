/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.postgres.repository;

import com.reto.postgres.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
