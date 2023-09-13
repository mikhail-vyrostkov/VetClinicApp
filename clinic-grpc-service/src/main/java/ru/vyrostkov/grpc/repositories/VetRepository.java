package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyrostkov.grpc.models.Vet;
@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
}
