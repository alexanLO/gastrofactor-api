package br.com.coccionapi.factorcc.adapter.output.database.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "cookingFactor")
public class CookingFactorEntity {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    // * id do ingrediente */
    @OneToOne
    @JoinColumn(name = "ingId", nullable = false)
    private IngredientEntity ingId = null;

    // * pesoCru */
    @Column(name = "rawWgh", nullable = false)
    private BigDecimal rawWgh = BigDecimal.ZERO;

    // * pesoCozido */
    @Column(name = "cookedWgt", nullable = false)
    private BigDecimal cookedWgt = BigDecimal.ZERO;

    // * resultado do calculo do fator de cocção */
    @Column(name = "cocFactor", nullable = false)
    private BigDecimal ccFactor = BigDecimal.ZERO;

    // * data do calculo */
    @Column(name = "calcAt", nullable = false)
    private LocalDateTime calcdAt = null;
}
