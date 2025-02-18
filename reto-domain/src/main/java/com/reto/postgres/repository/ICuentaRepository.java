/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.postgres.repository;

import com.reto.postgres.entity.CuentaEntity;
import com.reto.postgres.entity.EntidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interfaz.
 *
 * @author patedwins
 * @version 1.0.0
 */
public interface ICuentaRepository extends JpaRepository<CuentaEntity, Integer> {

    /**
     * Obtener cuenta por numero y entidad
     *
     * @param numCuenta
     * @param entidad
     * @return
     */
    CuentaEntity findByNumCuentaAndEntidad(String numCuenta, EntidadEntity entidad);

}
