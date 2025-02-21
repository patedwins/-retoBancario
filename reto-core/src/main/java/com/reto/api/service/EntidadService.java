/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.api.service.exception.util.MensajeConstantes;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.entity.PersonaEntity;
import com.reto.postgres.repository.IEntidadRepository;
import com.reto.util.Constantes;
import com.reto.vo.request.security.EntidadNewRequestVo;
import com.reto.vo.request.security.EntidadRequestVo;
import com.reto.vo.response.security.EntidadResponseVo;
import com.reto.vo.response.security.GeneralResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Base catálogo service implementation.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Service
public class EntidadService implements IEntidadService {

    private final transient IEntidadRepository entidadRepository;
    private final transient IUtilService iUtilService;

    /**
     * Constructor
     *
     * @param entidadRepository
     */
    public EntidadService(IEntidadRepository entidadRepository
            , IUtilService iUtilService) {
        this.entidadRepository = entidadRepository;
        this.iUtilService = iUtilService;
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
    public GeneralResponseVo saveNewEntidad(EntidadNewRequestVo data) {
        try {
            List<PersonaEntity> findEntidades = entidadRepository.findByNombre(data.getNombre());
            if (findEntidades.isEmpty()) {
                EntidadEntity newData = new EntidadEntity().toBuilder()
                        .nombre(data.getNombre())
                        .estado(Boolean.TRUE)
                        .build();
                entidadRepository.save(newData);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.ALMACENADO_OK);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "Ya existe la entidad a registrar");
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Update a persona
     *
     * @param data
     * @return a @{@link String}.
     */
    @Override
    public GeneralResponseVo updateEntidad(EntidadRequestVo data) {
        try {
            Optional<EntidadEntity> opEntidad = entidadRepository.findById(data.getId());
            if (opEntidad.isPresent()) {
                EntidadEntity actEntidad = opEntidad.get();
                actEntidad.setNombre(data.getNombre());
                actEntidad.setEstado(data.getEstado());
                entidadRepository.save(actEntidad);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_UPDATE);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la entidad para su actualización");
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Delete a persona
     *
     * @param data
     * @return a @{@link String} list.
     */
    @Override
    public GeneralResponseVo deleteEntidad(EntidadRequestVo data) throws RetoException {
        try {
            Optional<EntidadEntity> opEntidad = entidadRepository.findById(data.getId());
            if (opEntidad.isPresent()) {
                EntidadEntity actEntidad = opEntidad.get();
                entidadRepository.delete(actEntidad);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_UPDATE);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su eliminación");
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Find all entidad
     *
     * @return a @{@link String} list.
     */
    @Override
    public GeneralResponseVo findAllEntidad() {
        try {
            List<EntidadEntity> listPerson = entidadRepository.findAll();
            List<EntidadResponseVo> retorno = listPerson.stream()
                    .map(entidad -> EntidadResponseVo.builder()
                            .id(entidad.getId())
                            .nombre(entidad.getNombre())
                            .estado(entidad.getEstado())
                            .build())
                    .collect(Collectors.toList());
            return iUtilService.asignarGeneralResponse(retorno, HttpStatus.OK, Constantes.CONSULTA_CORRECTA);
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}