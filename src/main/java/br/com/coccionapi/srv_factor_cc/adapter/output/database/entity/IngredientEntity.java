package br.com.coccionapi.srv_factor_cc.adapter.output.database.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "ingredient")
public class IngredientEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "grossWgt", nullable = false)
    private BigDecimal grossWgt = BigDecimal.ZERO;

    @Column(name = "netWgt", nullable = false)
    private BigDecimal netWgt = BigDecimal.ZERO;

    @Column(name = "rawWgt", nullable = false)
    private BigDecimal rawWgt = BigDecimal.ZERO;

    @Column(name = "cookedWgt", nullable = false)
    private BigDecimal cookedWgt = BigDecimal.ZERO;

    @Column(name = "regisDate", nullable = false)
    private LocalDateTime regisDate = null;
}
