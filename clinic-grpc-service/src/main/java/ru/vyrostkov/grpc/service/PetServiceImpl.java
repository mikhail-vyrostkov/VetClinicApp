package ru.vyrostkov.grpc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.vyrostkov.grpc.dto.PetRequestDTO;
import ru.vyrostkov.grpc.dto.PetResponseDTO;
import ru.vyrostkov.grpc.models.Pet;
import ru.vyrostkov.grpc.repositories.PetRepository;
import ru.vyrostkov.grpc.repositories.PetTypeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public int createPet(PetRequestDTO pet) {
        Pet createPet = Pet
                .builder()
                .name(pet.petName())
                .petType(petTypeRepository.findByName(pet.petType()))
                .birthDate(LocalDate.parse(pet.petBirthDate()))
                .build();

        petRepository.save(createPet);

        return createPet.getId();
    }

    @Override
    public PetResponseDTO findByIDPet(int id) {

        Optional<Pet> pet = petRepository.findById(id);

        return pet.map(value -> PetResponseDTO
                .builder()
                .petName(value.getName())
                .petType(petTypeRepository.findById(
                        value.getPetType().getId()).get().getName())

                .build()).orElse(null);

    }

    @Override
    public List<PetResponseDTO> findAllPet() {
        List<Pet> pets = petRepository.findAll();
        return addList(pets);
    }

    @Override
    public List<PetResponseDTO> findAllByNamePet(String name) {
        List<Pet> pets = petRepository.findAllByName(name);
        return addList(pets);
    }

    @Override
    public void deletePet(int petId) {

    }

    @Override
    public PetResponseDTO updatePet(PetRequestDTO pet) {
        return null;
    }

    private List<PetResponseDTO> addList(List<Pet> pets){
        List<PetResponseDTO> petResponseDTOList = pets.stream().map(pet -> PetResponseDTO
                .builder()
                .petName(pet.getName())
                .petType(pet.getPetType().getName())
                .build()).collect(Collectors.toList());
        return petResponseDTOList;
    }
}
