package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyrostkov.grpc.models.Visit;
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
