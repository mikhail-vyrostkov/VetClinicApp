package ru.vyrostkov.grpc.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vyrostkov.grpc.GrpcApplication;
import ru.vyrostkov.grpc.models.Pet;
import ru.vyrostkov.grpc.models.PetType;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = GrpcApplication.class)
public class PetRepositoryTest extends PgContainerTest {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetTypeRepository petTypeRepository;

    private Pet pet;

    @BeforeEach
    public void setupTestData() {
        //Given


        pet = Pet.builder()
                .name("Bob")
                .petType(new PetType("dog"))
                .birthDate(LocalDate.of(2010, 7, 28))
                .build();
    }

    @Test
    @DisplayName("JUnit test for save pet")
    public void givenPetObject_whenSave_thenReturnSavePet() {
        //When
        Pet savePet = petRepository.save(pet);

        //Then
        assertNotNull(savePet);
        assertNotNull(savePet.getId());
    }

    @Test
    @DisplayName("JUnit test for select pet by his name")
    public void givenPetName_whenFindByName_thenReturnPet() {
        //When
        List<Pet> findPet = petRepository.findAllByName(pet.getName());

        //Then
        assertNotNull(findPet);
    }

    @Test
    @DisplayName("JUnit test for select pet by his name")
    public void givenPetType_whenFindAllByPetType_thenReturnPet() {

        //When
        PetType petType = petTypeRepository.findByName(pet.getPetType().getName());
        List<Pet> findPet = petRepository.findAllByPetType(petType);

        //Then
        assertNotNull(findPet);
    }
}
