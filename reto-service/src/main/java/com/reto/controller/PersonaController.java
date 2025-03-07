/*
 * Copyright (c) 2025.
 *
 *
 * Todos los derechos reservados
 */

package com.reto.controller;

import com.reto.api.service.IPersonaService;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.entity.PersonaEntity;
import com.reto.util.Constantes;
import com.reto.vo.request.security.PersonaNewRequestVo;
import com.reto.vo.request.security.PersonaRequestVo;
import com.reto.vo.request.security.UsuarioNewRequestVo;
import com.reto.vo.response.security.GeneralResponseVo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/persona")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class PersonaController {

    private final transient IPersonaService personaService;

    /**
     * Controller
     *
     * @param personaService
     */

    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    /**
     * Find all person.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @GetMapping(value = "obtenerListaPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> findAll() {
        GeneralResponseVo responseVo = personaService.findAllPerson();
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * New person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @PostMapping(value = "nuevaPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> newPerson(@RequestBody @NotNull PersonaNewRequestVo person, HttpServletRequest request) {
        GeneralResponseVo responseVo = personaService.saveNewPerson(person);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Update person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @PutMapping(value = "actualizarPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> updatePerson(@RequestBody @NotNull PersonaRequestVo person, HttpServletRequest request) {
        GeneralResponseVo responseVo = personaService.updatePerson(person);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * Delete person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @DeleteMapping(value = "eliminarPersona", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> deletePerson(@RequestBody @NotNull PersonaRequestVo person, HttpServletRequest request) {
        GeneralResponseVo responseVo = personaService.deletePerson(person);
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }

    /**
     * New person.
     *
     * @return a @{@link PersonaEntity} string.
     */
    @PostMapping(value = "nuevoUsuario", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> newUser(@RequestBody @NotNull UsuarioNewRequestVo person, HttpServletRequest request) {
        try {
            GeneralResponseVo responseVo = personaService.saveNewUser(person);
            return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GeneralResponseVo().toBuilder()
                            .object(null)
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .status(Constantes.ERRONEO)
                            .build()
                    , HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Find all person.
     *
     * @return a @{@link EntidadEntity} list.
     */
    @GetMapping(value = "obtenerListaUsuario", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<GeneralResponseVo> findAllUsers() {
        GeneralResponseVo responseVo = personaService.findAllUsers();
        return new ResponseEntity<>(responseVo, responseVo.getHttpStatus());
    }
}
