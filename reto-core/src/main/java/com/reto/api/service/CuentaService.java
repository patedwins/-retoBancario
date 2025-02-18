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
import com.reto.postgres.entity.CuentaClienteEntity;
import com.reto.postgres.entity.CuentaEntity;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.repository.IClienteRepository;
import com.reto.postgres.repository.ICuentaClienteRepository;
import com.reto.postgres.repository.ICuentaRepository;
import com.reto.postgres.repository.IEntidadRepository;
import com.reto.util.Constantes;
import com.reto.vo.CuentaClienteVo;
import com.reto.vo.CuentaVo;
import com.reto.vo.response.security.CuentaResponseVo;
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
public class CuentaService implements ICuentaService {

    private final transient ICuentaRepository cuentaRepository;
    private final transient IEntidadRepository entidadRepository;
    private final transient IClienteRepository clienteRepository;
    private final transient ICuentaClienteRepository cuentaClienteRepository;
    private final transient IUtilService iUtilService;

    /**
     * Constructor
     *
     * @param cuentaRepository
     */
    public CuentaService(ICuentaRepository cuentaRepository, IEntidadRepository entidadRepository
            , IClienteRepository clienteRepository, ICuentaClienteRepository cuentaClienteRepository, IUtilService iUtilService) {
        this.cuentaRepository = cuentaRepository;
        this.entidadRepository = entidadRepository;
        this.clienteRepository = clienteRepository;
        this.cuentaClienteRepository = cuentaClienteRepository;
        this.iUtilService = iUtilService;
    }

    /**
     * Save new entidad
     *
     * @param data
     * @return a @{@link String} .
     */
    @Override
    public GeneralResponseVo saveNewCuenta(CuentaVo data){
        try {
            Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
            if (entidadOp.isPresent()) {
                CuentaEntity newData = new CuentaEntity().toBuilder()
                        .entidad(entidadOp.get())
                        .numCuenta(data.getNumCuenta())
                        .tipo(data.getTipo())
                        .saldoInicial(data.getSaldoInicial())
                        .saldoDisponible(data.getSaldoInicial())
                        .estado(Boolean.TRUE)
                        .build();
                cuentaRepository.save(newData);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.ALMACENADO_OK);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la entidad para la cuenta");
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
    public GeneralResponseVo updateCuenta(CuentaVo data) throws RetoException {
        try {
            Optional<CuentaEntity> opCuenta = cuentaRepository.findById(data.getId());
            if (opCuenta.isPresent()) {
                Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
                if (entidadOp.isPresent()) {
                    CuentaEntity newData = opCuenta.get();
                    newData.setEntidad(entidadOp.get());
                    newData.setNumCuenta(data.getNumCuenta());
                    newData.setTipo(data.getTipo());
                    newData.setSaldoInicial(data.getSaldoInicial());
                    newData.setSaldoDisponible(data.getSaldoInicial());
                    newData.setEstado(Boolean.TRUE);
                    cuentaRepository.save(newData);
                    return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_UPDATE);
                } else {
                    return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la entidad para la cuenta");
                }
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
    public GeneralResponseVo deleteCuenta(CuentaVo data) throws RetoException {
        try {
            Optional<CuentaEntity> opCuenta = cuentaRepository.findById(data.getId());
            if (opCuenta.isPresent()) {
                CuentaEntity actEntidad = opCuenta.get();
                cuentaRepository.delete(actEntidad);
                return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_UPDATE);
            } else {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la persona para su eliminación");
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
    public GeneralResponseVo crearCuentaCliente(CuentaClienteVo data) throws RetoException {
        try {
            Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
            if (!entidadOp.isPresent()) {
                return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la entidad para la cuenta");
            } else {
                Optional<ClienteEntity> clienteOp = clienteRepository.findById(data.getIdCliente());
                if (!clienteOp.isPresent()) {
                    return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró el cliente");
                }
                CuentaEntity cuentaExistente = cuentaRepository.findByNumCuentaAndEntidad(data.getNumCuenta(), entidadOp.get());
                CuentaClienteEntity cuentaClienteExistente = cuentaClienteRepository.findByCuentaAndCliente(cuentaExistente, clienteOp.get());
                if (cuentaExistente != null && cuentaClienteExistente == null) {
                    CuentaClienteEntity cuentaCliente = new CuentaClienteEntity();
                    cuentaCliente.setCliente(clienteOp.get());
                    cuentaCliente.setCuenta(cuentaExistente);
                    cuentaClienteRepository.save(cuentaCliente);
                    return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_NEW);
                } else {
                    CuentaEntity newData = new CuentaEntity();
                    newData.setEntidad(entidadOp.get());
                    newData.setNumCuenta(data.getNumCuenta());
                    newData.setTipo(data.getTipo());
                    newData.setSaldoInicial(data.getSaldoInicial());
                    newData.setSaldoDisponible(data.getSaldoInicial());
                    newData.setEstado(Boolean.TRUE);
                    cuentaRepository.save(newData);
                    CuentaClienteEntity cuentaCliente = new CuentaClienteEntity();
                    cuentaCliente.setCliente(clienteOp.get());
                    cuentaCliente.setCuenta(newData);
                    cuentaClienteRepository.save(cuentaCliente);
                    return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, MensajeConstantes.SAVE_NEW);
                }
            }
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Find all cuenta
     *
     * @return a @{@link String} list.
     */
    @Override
    public GeneralResponseVo findAllCuenta() {
        try {
            List<CuentaEntity> listPerson = cuentaRepository.findAll();
            List<CuentaResponseVo> retorno = listPerson.stream()
                    .map(cuenta -> CuentaResponseVo.builder()
                            .id(cuenta.getId())
                            .entidad(new EntidadResponseVo().toBuilder().nombre(cuenta.getEntidad().getNombre())
                                    .estado(cuenta.getEntidad().getEstado()).build())
                            .numCuenta(cuenta.getNumCuenta())
                            .tipo(cuenta.getTipo())
                            .saldoInicial(cuenta.getSaldoInicial())
                            .saldoDisponible(cuenta.getSaldoDisponible())
                            .estado(cuenta.getEstado())
                            .build())
                    .collect(Collectors.toList());
            return iUtilService.asignarGeneralResponse(retorno, HttpStatus.OK, Constantes.CONSULTA_CORRECTA);
        } catch (Exception e) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}