/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.repository.IEntidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Base catálogo service implementation.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Service
public class EntidadService implements IEntidadService {

    private final transient IEntidadRepository entidadRepository;

    /**
     * Constructor
     *
     * @param entidadRepository
     */
    public EntidadService(IEntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @Override
    public List<EntidadEntity> findAll() {
        return entidadRepository.findAll();
    }

    /**
     * Save new entidad
     *
     * @param data
     * @return a @{@link String} .
     */
    @Override
    public String saveNewEntidad(EntidadEntity data) throws RetoException {
        EntidadEntity newData = new EntidadEntity();
        newData.setNombre(data.getNombre());
        newData.setEstado(Boolean.TRUE);
        entidadRepository.save(newData);
        return null;
    }

    /**
     * Update a persona
     *
     * @param data
     * @return a @{@link String}.
     */
    @Override
    public String updateEntidad(EntidadEntity data) throws RetoException {
        Optional<EntidadEntity> opEntidad = entidadRepository.findById(data.getId());
        if (opEntidad.isPresent()) {
            EntidadEntity actEntidad = opEntidad.get();
            actEntidad.setNombre(data.getNombre());
            entidadRepository.save(actEntidad);
            return null;
        } else {
            return "No se encontró la entidad para su actualización";
        }
    }

    /**
     * Delete a persona
     *
     * @param data
     * @return a @{@link String} list.
     */
    @Override
    public String deleteEntidad(EntidadEntity data) throws RetoException {
        Optional<EntidadEntity> opEntidad = entidadRepository.findById(data.getId());
        if (opEntidad.isPresent()) {
            EntidadEntity actEntidad = opEntidad.get();
            entidadRepository.delete(actEntidad);
            return null;
        } else {
            return "No se encontró la persona para su eliminación";
        }
    }
}