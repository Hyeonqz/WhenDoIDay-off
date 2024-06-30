package org.example.whereismyvacation.vacation.repository;

import org.example.whereismyvacation.vacation.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation,Long> {
}
