package fr.milleis.test.backend.repository;

import fr.milleis.test.backend.model.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Transfer, Long> {
}
