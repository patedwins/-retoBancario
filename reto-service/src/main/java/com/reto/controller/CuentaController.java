/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.controller;

import com.reto.api.service.ICuentaService;
import com.reto.postgres.entity.CuentaEntity;
import com.reto.vo.CuentaClienteVo;
import com.reto.vo.CuentaVo;
import com.reto.vo.response.security.GeneralResponseVo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * CatalogoController.
 *
 * @author patedwins
 * @version 1.0.0
 */
@RestController
@RequestMapping("/cuenta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class CuentaController {

    private final transient ICuentaService cuentaService;

    /**
     * Controller
     *
     * @param cuentaService
     */

    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    /**
     * Find all entidad.
     *
     * @return a @{@link CuentaEntity} list.
     */
    @GetMapping(value = "obtenerListaCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> findAll() {
        GeneralResponseVo responseVo = cuentaService.findAllCuenta();
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * New Entidad.
     *
     * @return a @{@link CuentaEntity} string.
     */
    @PostMapping(value = "nuevaCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> newCuenta(@RequestBody @NotNull CuentaVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = cuentaService.saveNewCuenta(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Update Entidad.
     *
     * @return a @{@link CuentaEntity} string.
     */
    @PutMapping(value = "actualizarCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> updateCuenta(@RequestBody CuentaVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = cuentaService.updateCuenta(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Delete Entidad.
     *
     * @return a @{@link CuentaEntity} string.
     */
    @DeleteMapping(value = "eliminarCuenta", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> deleteCuenta(@RequestBody CuentaVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = cuentaService.deleteCuenta(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Crear cuenta y asignar cuenta cliente.
     *
     * @return a @{@link CuentaClienteVo} string.
     */
    @PostMapping(value = "nuevaCuentaCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> crearCuentaCliente(@RequestBody CuentaClienteVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = cuentaService.crearCuentaCliente(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }
}
