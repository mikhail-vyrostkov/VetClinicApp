//package ru.vyrostkov.grpc.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import ru.vyrostkov.grpc.dto.PetRequestDTO;
//import ru.vyrostkov.grpc.dto.PetResponseDTO;
//import ru.vyrostkov.grpc.models.Pet;
//import ru.vyrostkov.grpc.repositories.PetRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class PetServiceImpl implements PetService {
//
//    private final PetRepository petRepository;
//
//    @Override
//    public int createPet(PetRequestDTO pet) {
//        return 0;
//    }
//
//    @Override
//    public PetResponseDTO findByIDPet(long id) {
//
//        Optional<Pet> pet = petRepository.findById(id);
//        return pet.map(value -> PetResponseDTO
//                .builder()
//                .petName(value.getName())
//                .petType(value.getPetType().getName())
//                .petBirthDate(value.getBirthDate().toString())
//                .vaccinationName(value.getVaccination().getName())
//                .vaccinationDate(value.getVaccination().getVaccinationDate().toString())
//                .ownerFirstName(value.getOwner().getFirstName())
//                .ownerLastName(value.getOwner().getLastName())
//                .ownerMiddleName(value.getOwner().getMiddleName())
//                .build()).orElse(null);
//    }
//
//    @Override
//    public List<PetResponseDTO> findAllPet() {
//        return null;
//    }
//
//    @Override
//    public List<PetResponseDTO> findAllByNamePet(String Name) {
//        return null;
//    }
//
//    @Override
//    public void deletePet(long petId) {
//
//    }
//
//    @Override
//    public PetResponseDTO updatePet(PetRequestDTO pet) {
//        return null;
//    }
//}
