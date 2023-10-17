package ru.vyrostkov.grpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vyrostkov.grpc.models.Pet;
import ru.vyrostkov.grpc.models.PetType;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findAllByPetType(PetType petType);
    List<Pet> findAllByName(String name);
}
