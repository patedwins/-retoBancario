/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.reto.api.service;

import com.reto.api.service.exception.RetoException;
import com.reto.api.service.exception.util.MensajeConstantes;
import com.reto.postgres.entity.ClienteEntity;
import com.reto.postgres.entity.CuentaClienteEntity;
import com.reto.postgres.entity.CuentaEntity;
import com.reto.postgres.entity.EntidadEntity;
import com.reto.postgres.entity.MovimientoEntity;
import com.reto.postgres.repository.IClienteRepository;
import com.reto.postgres.repository.ICuentaClienteRepository;
import com.reto.postgres.repository.ICuentaRepository;
import com.reto.postgres.repository.IEntidadRepository;
import com.reto.postgres.repository.IMovimientoRepository;
import com.reto.util.Constantes;
import com.reto.vo.MovimientoFechasVo;
import com.reto.vo.MovimientoRegistarVo;
import com.reto.vo.MovimientoVo;
import com.reto.vo.response.security.GeneralResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Base catálogo service implementation.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Service
public class MovimientoService implements IMovimientoService {

    private final IMovimientoRepository movimientoRepository;
    private final IEntidadRepository entidadRepository;
    private final ICuentaRepository cuentaRepository;
    private final IClienteRepository clienteRepository;
    private final ICuentaClienteRepository cuentaClienteRepository;
    private final IUtilService iUtilService;
    private static final DateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH);

    /**
     * Constructor
     *
     * @param movimientoRepository
     */
    public MovimientoService(IMovimientoRepository movimientoRepository,
                             IEntidadRepository entidadRepository,
                             ICuentaRepository cuentaRepository,
                             IClienteRepository clienteRepository,
                             ICuentaClienteRepository cuentaClienteRepository,
                             IUtilService iUtilService) {
        this.movimientoRepository = movimientoRepository;
        this.entidadRepository = entidadRepository;
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
        this.cuentaClienteRepository = cuentaClienteRepository;
        this.iUtilService = iUtilService;
    }

    /**
     * Find todos los movimientos
     *
     * @param idEntidad
     * @return a @{@link MovimientoVo} list.
     */
    @Override
    public GeneralResponseVo obtenerMovimientoPorEntidad(Integer idEntidad) {
        Optional<EntidadEntity> entidad = entidadRepository.findById(idEntidad);
        if (entidad.isPresent()) {
            List<MovimientoEntity> listMovimiento = movimientoRepository.obtenerPorEntidad(entidad.get().getId(), Boolean.TRUE);
            List<MovimientoVo> retorno = listMovimiento.stream()
                    .map(mov -> MovimientoVo.builder()
                            .idEntidad(idEntidad)
                            .idCuenta(mov.getCuentaCliente().getCuenta().getId())
                            .entidad(mov.getCuentaCliente().getCuenta().getEntidad().getNombre())
                            .id(mov.getId())
                            .fecMovimiento(mov.getFecMovimiento())
                            .idCliente(mov.getCuentaCliente().getCliente().getId())
                            .cliente(mov.getCuentaCliente().getCliente().getPersona().getNombre())
                            .idCuenta(mov.getCuentaCliente().getCuenta().getId())
                            .numCuenta(mov.getCuentaCliente().getCuenta().getNumCuenta())
                            .tipoCuenta(mov.getCuentaCliente().getCuenta().getTipo())
                            .tipoMovimiento(mov.getDescripcion())
                            .saldoInicial(mov.getCuentaCliente().getCuenta().getSaldoInicial())
                            .valorMovimiento(mov.getValor())
                            .saldoDisponibleCuenta(mov.getCuentaCliente().getCuenta().getSaldoDisponible())
                            .saldoDisponibleFechaCuenta(mov.getSaldoCuentaFecha())
                            .estadoCuenta(mov.getCuentaCliente().getCuenta().getEstado())
                            .build())
                    .collect(Collectors.toList());
            return iUtilService.asignarGeneralResponse(retorno, HttpStatus.OK, Constantes.CONSULTA_CORRECTA);
        } else {
            return iUtilService.asignarGeneralResponse(new ArrayList<>(), HttpStatus.BAD_REQUEST, Constantes.NO_DATOS);
        }
    }

    /**
     * Find all movimientos por entidad
     *
     * @param movimiento
     * @return a @{@link MovimientoVo} list.
     */
    @Override
    public GeneralResponseVo generarMovimientoPorEntidad(MovimientoRegistarVo movimiento) {
        Optional<CuentaEntity> cuentaOp = cuentaRepository.findById(movimiento.getIdCuenta());
        if (!cuentaOp.isPresent()) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la cuenta");
        }
        Optional<ClienteEntity> clienteOp = clienteRepository.findById(movimiento.getIdCliente());
        if (!clienteOp.isPresent()) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró al cliente");
        }
        CuentaEntity cuenta = cuentaOp.get();
        CuentaClienteEntity cuentaCliente = cuentaClienteRepository.findByCuentaAndCliente(cuenta, clienteOp.get());
        if (cuentaCliente == null) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, "No se encontró la cuenta asociada al cliente");
        }
        MovimientoEntity newMovimiento = new MovimientoEntity();
        BigDecimal saldoDisponible = cuentaOp.get().getSaldoDisponible();
        newMovimiento.setCuentaCliente(cuentaCliente);
        if (movimiento.getValorMovimiento().compareTo(BigDecimal.ZERO) >= 0) {
            newMovimiento.setTipo(Constantes.DEPOSITO);
            newMovimiento.setDescripcion(Constantes.DEPOSITO.concat(" ").concat(movimiento.getValorMovimiento().toString()));
        } else {
            newMovimiento.setTipo(Constantes.RETIRO);
            newMovimiento.setDescripcion(Constantes.RETIRO.concat(" ").concat(movimiento.getValorMovimiento().toString()));
        }
        saldoDisponible = saldoDisponible.add(movimiento.getValorMovimiento());
        newMovimiento.setSaldoCuentaFecha(saldoDisponible);
        newMovimiento.setValor(movimiento.getValorMovimiento());
        newMovimiento.setFecMovimiento(new Date());
        cuenta.setSaldoDisponible(saldoDisponible);
        movimientoRepository.save(newMovimiento);
        cuentaRepository.save(cuenta);
        return iUtilService.asignarGeneralResponse(null, HttpStatus.OK, Constantes.GENERAR_MOVIMIENTO);
    }

    /**
     * Find all movimientos por entidad
     *
     * @param fecDesde
     * @param fecHasta
     * @return a @{@link MovimientoVo} list.
     */
    @Override
    public GeneralResponseVo obtenerMovimientoPorFecha(String fecDesde, String fecHasta) throws RetoException {
        if (fecDesde == null || fecHasta == null) {
            return iUtilService.asignarGeneralResponse(null, HttpStatus.BAD_REQUEST, MensajeConstantes.OBLIGATORIO_FECHAS);
        }
        List<MovimientoEntity> listMovimiento = movimientoRepository.obtenerPorFechas(
                convertirStringToDate(fecDesde.concat(" 00:00:00")), convertirStringToDate(fecHasta.concat(" 23:59:59")));
        List<MovimientoFechasVo> retorno = new ArrayList<>();
        listMovimiento.stream().forEach(mov -> {
            MovimientoFechasVo movData = new MovimientoFechasVo().toBuilder()
                    .fecha(mov.getFecMovimiento())
                    .cliente(mov.getCuentaCliente().getCliente().getPersona().getNombre())
                    .numeroCuenta(mov.getCuentaCliente().getCuenta().getNumCuenta())
                    .tipo(mov.getCuentaCliente().getCuenta().getTipo())
                    .saldoInicial(mov.getCuentaCliente().getCuenta().getSaldoInicial())
                    .movimiento(mov.getValor())
                    .saldoDisponible(mov.getSaldoCuentaFecha())
                    .estado(mov.getCuentaCliente().getCuenta().getEstado())
                    .build();
            retorno.add(movData);
        });
        return iUtilService.asignarGeneralResponse(retorno, HttpStatus.OK, Constantes.CONSULTA_CORRECTA);
    }

    /**
     * Permite dar formato a la fecha
     *
     * @return fecha con formato
     */
    public Date convertirStringToDate(String fechaString) {
        try {
            synchronized (FORMATO) {
                return FORMATO.parse(fechaString);
            }
        } catch (ParseException ex) {
            throw new RetoException(HttpStatus.BAD_REQUEST, MensajeConstantes.FORMATO_FECHAS);
        }
    }
}