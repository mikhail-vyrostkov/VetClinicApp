package ru.vyrostkov.grpc.service;

import ru.vyrostkov.grpc.dto.PetRequestDTO;
import ru.vyrostkov.grpc.dto.PetResponseDTO;

import java.util.List;

public interface PetService {

    int createPet(PetRequestDTO pet);

    PetResponseDTO findByIDPet(int id);

    List<PetResponseDTO> findAllPet();

    List<PetResponseDTO> findAllByNamePet(String Name);

    void deletePet(int petId);

    PetResponseDTO updatePet(PetRequestDTO pet);
}
