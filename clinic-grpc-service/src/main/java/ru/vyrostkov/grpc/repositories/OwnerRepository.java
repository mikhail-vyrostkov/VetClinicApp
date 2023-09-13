package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyrostkov.grpc.models.Owner;

import java.util.List;


public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findAllByLastName(String lastName);
}
