/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.controller;

import com.reto.api.service.IMovimientoService;
import com.reto.vo.MovimientoRegistarVo;
import com.reto.vo.MovimientoVo;
import com.reto.vo.response.security.GeneralResponseVo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/movimiento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class MovimientoController {

    private final transient IMovimientoService movimientoService;

    /**
     * Controller
     *
     * @param movimientoService
     */

    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link MovimientoVo} list.
     */
    @GetMapping(value = "obtenerMovimientosPorEntidad", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Object> findAllMovimiento(@NotNull @RequestParam("idEntidad") Integer idEntidad
            , HttpServletRequest request) {
        GeneralResponseVo responseVo = movimientoService.obtenerMovimientoPorEntidad(idEntidad);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link MovimientoVo} list.
     */
    @PostMapping(value = "crearMovimientosPorCuentaYCliente", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> asiganarMovimiento(@RequestBody MovimientoRegistarVo movimiento
            , HttpServletRequest request) {
        GeneralResponseVo responseVo = movimientoService.generarMovimientoPorEntidad(movimiento);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link MovimientoVo} list.
     */
    @GetMapping(value = "obtenerMovimientosPorFecha", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Object> findAllMovimientoPorFecha(@NotNull @RequestParam("fecDesde") String fecDesde
            , @NotNull @RequestParam("fecHasta") String fecHasta, HttpServletRequest request) {
        GeneralResponseVo responseVo = movimientoService.obtenerMovimientoPorFecha(fecDesde, fecHasta);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }
}
