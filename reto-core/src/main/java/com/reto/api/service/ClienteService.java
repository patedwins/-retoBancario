/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.api.service.exception.util.MensajeConstantes;
import com.reto.postgres.entity.ClienteEntity;
import com.reto.postgres.entity.PersonaEntity;
import com.reto.postgres.repository.IClienteRepository;
import com.reto.postgres.repository.IPersonaRepository;
import com.reto.util.Constantes;
import com.reto.vo.ClienteVo;
import com.reto.vo.response.security.ClienteResponseVo;
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
public class ClienteService implements IClienteService {

    private final transient IClienteRepository clienteRepository;
    private final transient IPersonaRepository personaRepository;
    private final transient IUtilService iUtilService;

    /**
     * Constructor
     *
     * @param clienteRepository
     */
    public ClienteService(IClienteRepository clienteRepository
            , IPersonaRepository personaRepository
            , IUtilService iUtilService) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
        this.iUtilService = iUtilService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link ClienteEntity} list.
     */
    @Override
    public GeneralResponseVo findAll() {
        try {
            List<ClienteEntity> listPerson = clienteRepository.findAll();
            List<ClienteResponseVo> retorno = listPerson.stream()
                    .map(cliente -> ClienteResponseVo.builder()
                            .id(cliente.getId())
                            .persona(new PersonaResponseVo().toBuilder().nombre(cliente.getPersona().getNombre())
                                    .identificacion(cliente.getPersona().getIdentificacion())
                                    .estado(cliente.getPersona().getEstado()).build())
                            .clienteId(cliente.getClienteid())
                            .contrasena(cliente.getContrasena())
                            .estado(cliente.getEstado())
                            .build())
                    .collect(Collectors.toList());
            return iUtilService.asignarGeneralResponse(retorno, HttpStatus.OK, Constantes.CONSULTA_CORRECTA);
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Save new entidad
     *
     * @param data
     * @return a @{@link String} .
     */
    @Override
    public GeneralResponseVo saveNewCliente(ClienteVo data) throws RetoException {
        try {
            Optional<PersonaEntity> personaOp = personaRepository.findById(data.getIdPersona());
            if (!personaOp.isPresent()) {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su creación");
            }
            PersonaEntity persona = personaOp.get();
            ClienteEntity newData = new ClienteEntity().toBuilder()
                    .clienteid(data.getClienteId())
                    .persona(persona)
                    .contrasena(data.getContrasena())
                    .estado(Boolean.TRUE)
                    .build();
            clienteRepository.save(newData);
            return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.ALMACENADO_OK);
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
    public GeneralResponseVo updateCliente(ClienteVo data) {
        try {
            Optional<ClienteEntity> clienteOp = clienteRepository.findById(data.getId());
            if (!clienteOp.isPresent()) {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró al cliente para su actualización");
            }
            Optional<PersonaEntity> personaOp = personaRepository.findById(data.getIdPersona());
            if (personaOp.isPresent()) {
                ClienteEntity newData = clienteOp.get();
                newData.setClienteid(data.getClienteId());
                newData.setPersona(personaOp.get());
                newData.setContrasena(data.getContrasena());
                newData.setEstado(Boolean.TRUE);
                clienteRepository.save(newData);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_UPDATE);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su creación");
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
    public GeneralResponseVo deleteCliente(ClienteVo data) {
        try {
            Optional<ClienteEntity> opEntidad = clienteRepository.findById(data.getId());
            if (opEntidad.isPresent()) {
                ClienteEntity actEntidad = opEntidad.get();
                clienteRepository.delete(actEntidad);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_UPDATE);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su eliminación");
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}