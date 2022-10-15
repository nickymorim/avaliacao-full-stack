package br.com.teste.repository;

import br.com.teste.domain.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
