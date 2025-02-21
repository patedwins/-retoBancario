/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.postgres.repository;

import com.reto.postgres.entity.ClienteEntity;
import com.reto.postgres.entity.CuentaClienteEntity;
import com.reto.postgres.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface ICuentaClienteRepository extends JpaRepository<CuentaClienteEntity, Integer> {

    /**
     * Obtener cuenta cliente por cuenta y cliente
     *
     * @param cuenta
     * @param cliente
     * @return
     */
    CuentaClienteEntity findByCuentaAndCliente(CuentaEntity cuenta, ClienteEntity cliente);

}
