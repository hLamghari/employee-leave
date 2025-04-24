package fr.milleis.test.backend.repository;

import fr.milleis.test.backend.model.entity.Transfer;
import fr.milleis.test.backend.model.enums.TransfertStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByStatusAndExecutionDateLessThanEqual(TransfertStatus status, LocalDate executionDate);
}