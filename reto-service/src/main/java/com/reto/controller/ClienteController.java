/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.controller;

import com.reto.api.service.IClienteService;
import com.reto.postgres.entity.ClienteEntity;
import com.reto.vo.ClienteVo;
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
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class ClienteController {

    private final transient IClienteService clienteService;

    /**
     * Controller
     *
     * @param clienteService
     */

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link ClienteEntity} list.
     */
    @GetMapping(value = "obtenerListaCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> findAll() {
        GeneralResponseVo responseVo = clienteService.findAll();
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * New Entidad.
     *
     * @return a @{@link ClienteEntity} string.
     */
    @PostMapping(value = "nuevoCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> newEntity(@RequestBody @NotNull ClienteVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = clienteService.saveNewCliente(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Update Entidad.
     *
     * @return a @{@link ClienteEntity} string.
     */
    @PutMapping(value = "actualizarCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> updateEntidad(@RequestBody ClienteVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = clienteService.updateCliente(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Delete Entidad.
     *
     * @return a @{@link ClienteEntity} string.
     */
    @DeleteMapping(value = "eliminarCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> deleteEntidad(@RequestBody ClienteVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = clienteService.deleteCliente(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }
}
