package fr.milleis.test.backend.entities;

import fr.milleis.test.backend.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate dateHiring;
    private BigDecimal saleLeave;
    @Column(name = "sale_rtt")
    private BigDecimal saleRTT;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Leave> leaves;

    public boolean isExecutive () {
        return Category.CADRE.equals(category);
    }

}
