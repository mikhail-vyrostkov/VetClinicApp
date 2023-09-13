package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyrostkov.grpc.models.Vaccination;
@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
}
