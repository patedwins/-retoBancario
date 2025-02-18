/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.postgres.entity.ClienteEntity;
import com.reto.postgres.entity.CuentaClienteEntity;
import com.reto.postgres.entity.CuentaEntity;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.repository.IClienteRepository;
import com.reto.postgres.repository.ICuentaClienteRepository;
import com.reto.postgres.repository.ICuentaRepository;
import com.reto.postgres.repository.IEntidadRepository;
import com.reto.vo.CuentaClienteVo;
import com.reto.vo.CuentaVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CuentaService implements ICuentaService {

    private final transient ICuentaRepository cuentaRepository;
    private final transient IEntidadRepository entidadRepository;
    @Autowired
    private transient IClienteRepository clienteRepository;
    @Autowired
    private transient ICuentaClienteRepository cuentaClienteRepository;

    /**
     * Constructor
     *
     * @param cuentaRepository
     */
    public CuentaService(ICuentaRepository cuentaRepository, IEntidadRepository entidadRepository) {
        this.cuentaRepository = cuentaRepository;
        this.entidadRepository = entidadRepository;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link CuentaEntity} list.
     */
    @Override
    public List<CuentaEntity> findAll() {
        return cuentaRepository.findAll();
    }

    /**
     * Save new entidad
     *
     * @param data
     * @return a @{@link String} .
     */
    @Override
    public String saveNewCuenta(CuentaVo data) throws RetoException {
        Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
        if (entidadOp.isPresent()) {
            CuentaEntity newData = new CuentaEntity();
            newData.setEntidad(entidadOp.get());
            newData.setNumCuenta(data.getNumCuenta());
            newData.setTipo(data.getTipo());
            newData.setSaldoInicial(data.getSaldoInicial());
            newData.setSaldoDisponible(data.getSaldoInicial());
            newData.setEstado(Boolean.TRUE);
            cuentaRepository.save(newData);
            return null;
        } else {
            return "No se encontró la entidad para la cuenta";
        }
    }

    /**
     * Update a persona
     *
     * @param data
     * @return a @{@link String}.
     */
    @Override
    public String updateCuenta(CuentaVo data) throws RetoException {
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
                return null;
            } else {
                return "No se encontró la entidad para la cuenta";
            }
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
    public String deleteCuenta(CuentaVo data) throws RetoException {
        Optional<CuentaEntity> opCuenta = cuentaRepository.findById(data.getId());
        if (opCuenta.isPresent()) {
            CuentaEntity actEntidad = opCuenta.get();
            cuentaRepository.delete(actEntidad);
            return null;
        } else {
            return "No se encontró la persona para su eliminación";
        }
    }

    /**
     * Update a persona
     *
     * @param data
     * @return a @{@link String}.
     */
    @Override
    public String crearCuentaCliente(CuentaClienteVo data) throws RetoException {
        Optional<EntidadEntity> entidadOp = entidadRepository.findById(data.getIdEntidad());
        if (!entidadOp.isPresent()) {
            return "No se encontró la entidad para la cuenta";
        } else {
            Optional<ClienteEntity> clienteOp = clienteRepository.findById(data.getIdCliente());
            if (!clienteOp.isPresent()) {
                return "No se encontró el cliente";
            }
            CuentaEntity cuentaExistente = cuentaRepository.findByNumCuentaAndEntidad(data.getNumCuenta(), entidadOp.get());
            CuentaClienteEntity cuentaClienteExistente = cuentaClienteRepository.findByCuentaAndCliente(cuentaExistente, clienteOp.get());
            if (cuentaExistente != null && cuentaClienteExistente == null) {
                CuentaClienteEntity cuentaCliente = new CuentaClienteEntity();
                cuentaCliente.setCliente(clienteOp.get());
                cuentaCliente.setCuenta(cuentaExistente);
                cuentaClienteRepository.save(cuentaCliente);
                return null;
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
                return null;
            }
        }
    }
}