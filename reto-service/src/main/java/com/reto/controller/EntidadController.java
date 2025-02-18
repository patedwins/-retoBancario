/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.controller;

import com.reto.api.service.IEntidadService;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.vo.request.security.EntidadNewRequestVo;
import com.reto.vo.request.security.EntidadRequestVo;
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
@RequestMapping("/entidad")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class EntidadController {

    private final transient IEntidadService entidadService;

    /**
     * Controller
     *
     * @param entidadService
     */

    public EntidadController(IEntidadService entidadService) {
        this.entidadService = entidadService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @GetMapping(value = "obtenerListaEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> findAll() {
        GeneralResponseVo responseVo = entidadService.findAllEntidad();
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * New Entidad.
     *
     * @return a @{@link EntidadEntity} string.
     */
    @PostMapping(value = "nuevaEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> newEntity(@RequestBody @NotNull EntidadNewRequestVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = entidadService.saveNewEntidad(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Update Entidad.
     *
     * @return a @{@link EntidadEntity} string.
     */
    @PutMapping(value = "actualizarEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> updateEntidad(@RequestBody @NotNull EntidadRequestVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = entidadService.updateEntidad(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Delete Entidad.
     *
     * @return a @{@link EntidadEntity} string.
     */
    @DeleteMapping(value = "eliminarEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> deleteEntidad(@RequestBody @NotNull EntidadRequestVo data, HttpServletRequest request) {
        GeneralResponseVo responseVo = entidadService.deleteEntidad(data);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }
}
