package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyrostkov.grpc.models.Speciality;
@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
