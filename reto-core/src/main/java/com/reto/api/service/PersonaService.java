/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.entity.PersonaEntity;
import com.reto.postgres.repository.IPersonaRepository;
import com.reto.util.Constantes;
import com.reto.vo.request.security.PersonaNewRequestVo;
import com.reto.vo.request.security.PersonaRequestVo;
import com.reto.vo.response.security.GeneralResponseVo;
import com.reto.vo.response.security.PersonaResponseVo;
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
public class PersonaService implements IPersonaService {

    private final transient IPersonaRepository personaRepository;
    private final IUtilService iUtilService;

    /**
     * Constructor
     *
     * @param personaRepository
     */
    public PersonaService(IPersonaRepository personaRepository
            , IUtilService iUtilService) {
        this.personaRepository = personaRepository;
        this.iUtilService = iUtilService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @Override
    public List<PersonaEntity> findAll() {
        return personaRepository.findAll();
    }

    /**
     * Save new persona
     *
     * @param person
     * @return a @{@link EntidadEntity} list.
     */
    @Override
    public GeneralResponseVo saveNewPerson(PersonaNewRequestVo person) {
        try {
            List<PersonaEntity> findPersons = personaRepository.findByIdentificacion(person.getIdentificacion());
            if (findPersons.isEmpty()) {
                PersonaEntity newPerson = new PersonaEntity().toBuilder()
                        .nombre(person.getNombre())
                        .edad(person.getEdad())
                        .direccion(person.getDireccion())
                        .genero(person.getGenero())
                        .identificacion(person.getIdentificacion())
                        .telefono(person.getTelefono())
                        .estado(Boolean.TRUE)
                        .build();
                personaRepository.save(newPerson);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.ALMACENADO_OK);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "Ya existe la persona con la identificación registrada");
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Update a persona
     *
     * @param person
     * @return a @{@link String} list.
     */
    @Override
    public GeneralResponseVo updatePerson(PersonaRequestVo person) {
        try {
            Optional<PersonaEntity> opPerson = personaRepository.findById(person.getId());
            if (opPerson.isPresent()) {
                PersonaEntity actPerson = opPerson.get();
                actPerson.setNombre(person.getNombre());
                actPerson.setEdad(person.getEdad());
                actPerson.setDireccion(person.getDireccion());
                actPerson.setGenero(person.getGenero());
                actPerson.setIdentificacion(person.getIdentificacion());
                actPerson.setTelefono(person.getTelefono());
                personaRepository.save(actPerson);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.ALMACENADO_OK);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su actualización");
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Delete a persona
     *
     * @param person
     * @return a @{@link String} list.
     */
    @Override
    public GeneralResponseVo deletePerson(PersonaRequestVo person) {
        try {
            Optional<PersonaEntity> opPerson = personaRepository.findById(person.getId());
            if (opPerson.isPresent()) {
                PersonaEntity actPerson = opPerson.get();
                personaRepository.delete(actPerson);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.ALMACENADO_OK);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su eliminación");
            }
        } catch (
                Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Find all persona
     *
     * @return a @{@link String} list.
     */
    @Override
    public GeneralResponseVo findAllPerson() {
        try {
            List<PersonaEntity> listPerson = personaRepository.findAll();
            List<PersonaResponseVo> retorno = listPerson.stream()
                    .map(persona -> PersonaResponseVo.builder()
                            .nombre(persona.getNombre())
                            .direccion(persona.getDireccion())
                            .genero(persona.getGenero())
                            .edad(persona.getEdad())
                            .estado(persona.getEstado())
                            .identificacion(persona.getIdentificacion())
                            .telefono(persona.getTelefono())
                            .build())
                    .collect(Collectors.toList());
            return iUtilService.asignarGeneralResponse(retorno, HttpStatus.OK, Constantes.CONSULTA_CORRECTA);
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}