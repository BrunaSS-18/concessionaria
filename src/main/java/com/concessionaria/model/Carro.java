package com.concessionaria.model;

import com.concessionaria.enums.StatusVenda;
import com.concessionaria.enums.TipoEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "carro")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer anoFabricacao;

    @Column(nullable = false)
    private Integer anoModelo;

    @Column(nullable = false)
    private String cor;

    @Column(unique = true)
    private String placa;

    @Column(nullable = false, unique = true)
    private String chassi;

    @Column(nullable = false)
    private Integer quilometragem;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVenda statusVenda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEstado tipoEstado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;
}