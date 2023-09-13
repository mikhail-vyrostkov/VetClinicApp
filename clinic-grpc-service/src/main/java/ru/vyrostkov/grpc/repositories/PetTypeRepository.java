package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyrostkov.grpc.models.PetType;
@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {

    PetType findByName(String name);
}
