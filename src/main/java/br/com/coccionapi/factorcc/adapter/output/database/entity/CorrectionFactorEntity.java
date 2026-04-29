package br.com.coccionapi.factorcc.adapter.output.database.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "correctionFactor")
public class CorrectionFactorEntity {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    // * id do ingrediente */
    @OneToOne
    @JoinColumn(name = "ingId", nullable = false)
    private IngredientEntity ingId = null;

    // * pesoBruto */
    @Column(name = "grossWgh", nullable = false)
    private BigDecimal grossWgh = BigDecimal.ZERO;

    // * pesoLiquido */
    @Column(name = "netWgh", nullable = false)
    private BigDecimal netWgh = BigDecimal.ZERO;

    // * resultado do calculo do fator de correção */
    @Column(name = "corFactor", nullable = false)
    private BigDecimal ctFactor = BigDecimal.ZERO;

    // * data do calculo */
    @Column(name = "calcAt", nullable = false)
    private LocalDateTime calcdAt = null;
}
