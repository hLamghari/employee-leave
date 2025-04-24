package fr.milleis.test.backend.model.entity;

import fr.milleis.test.backend.model.enums.TransfertStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transfers")
@Data
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account_id", nullable = false)
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", nullable = false)
    private Account destinationAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate executionDate;

    @Column
    private String periodicity; // null, "MONTHLY", "YEARLY"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransfertStatus status;
}