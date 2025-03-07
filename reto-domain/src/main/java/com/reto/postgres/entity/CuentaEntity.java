package com.reto.postgres.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Catálogo entity.
 *
 * @author patedwins
 * @version 1.0.0
 */
@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuenta", schema = "reto")
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_entidad", nullable = false)
    private EntidadEntity entidad;

    @Column(name = "num_cuenta", nullable = false, length = 50)
    private String numCuenta;

    @Column(name = "tipo", nullable = false, length = 30)
    private String tipo;

    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    @Column(name = "saldo_disponible")
    private BigDecimal saldoDisponible;

    @Column(name = "estado")
    private Boolean estado;
}