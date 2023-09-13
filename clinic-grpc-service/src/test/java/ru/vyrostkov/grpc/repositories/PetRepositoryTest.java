package ru.vyrostkov.grpc.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vyrostkov.grpc.TestApplication;
import ru.vyrostkov.grpc.models.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TestApplication.class)
public class PetRepositoryTest extends PgContainerTest {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetTypeRepository petTypeRepository;

    private Pet pet;
    private Owner owner;


    @BeforeEach
    public void setupTestData() {
        //Given
        owner = Owner.builder()
                .lastName("Vyrostkov")
                .firstName("Mikhail")
                .middleName("Yurievich")
                .age(28)
                .address("Moscow")
                .telephone("89297178370")
                .build();

        pet = Pet.builder()
                .name("Bob")
                .petType(new PetType("dog"))
                .birthDate(LocalDate.of(2010, 7, 28))
                .owner(owner)
                .vaccination(new Vaccination(
                        "covid19",
                        LocalDate.of(2020, 1, 8)
                ))
                .build();
    }

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

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
        List<Pet> findPet = petRepository.findByName(pet.getName());

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
