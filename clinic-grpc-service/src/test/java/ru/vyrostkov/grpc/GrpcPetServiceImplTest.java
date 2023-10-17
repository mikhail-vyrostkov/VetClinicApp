package ru.vyrostkov.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetOuterClass;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetServiceGrpc;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = GrpcApplication.class)
public class GrpcPetServiceImplTest {

    private Channel ch = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext()
            .build();

    private PetServiceGrpc.PetServiceBlockingStub stub = PetServiceGrpc.newBlockingStub(ch);



    @Test
    @DisplayName("JUnit grpc test for create pet")
    void createPetTest() {
        PetOuterClass.CreatePetResponse expected = PetOuterClass.CreatePetResponse
                .newBuilder()
                .setPetId(1)
                .build();

        PetOuterClass.CreatePetResponse response = stub.createPet(PetOuterClass.CreatePetRequest
                .newBuilder()
                .setPet(PetOuterClass.CreatePetRequest.Pet
                        .newBuilder()
                        .setPetId(1)
                        .setPetName("Bob")
                        .setPetType("Dog")
                        .setPetBirthDate("2010-11-03")
                        .setOwnerLastName("Vyrostkov")
                        .setOwnerFirstName("Mikhail")
                        .setOwnerMiddleName("Yurevich")
                        .setOwnerAge(28)
                        .setOwnerAddress("Moscow")
                        .setOwnerTelephone("89258127349")
                        .setVaccinationName("Covid_19")
                        .setVaccinationDate("2019-12-24")
                        .build())
                .build());

        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("JUnit grpc test for find pet by id")
    void findByIDPetTest() {
        PetOuterClass.FindByIdPetResponse expected = PetOuterClass.FindByIdPetResponse
                .newBuilder()
                .setPet(PetOuterClass.FindByIdPetResponse.Pet
                        .newBuilder()
                        .setPetName("Bob")
                        .setPetType("Dog")
                        .setPetBirthDate("2010-11-03")
                        .setOwnerLastName("Vyrostkov")
                        .setOwnerFirstName("Mikhail")
                        .setOwnerMiddleName("Yurevich")
                        .setVaccinationName("Covid_19")
                        .setVaccinationDate("2019-12-24")
                        .build())
                .build();

        PetOuterClass.FindByIdPetResponse response = stub.findByIDPet(PetOuterClass.FindByIdPetRequest
                .newBuilder()
                .setPetId(1)
                .build());

        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("JUnit grpc test for find all pets")
    void findAllPetTest() {
        Iterator<PetOuterClass.FindAllResponse> allPets = stub.findAllPet(PetOuterClass.FindAllRequest
                .newBuilder()
                .build());

        ImmutableList<PetOuterClass.FindAllResponse> allPetList = ImmutableList.copyOf(allPets);

        assertThat(allPetList).isNotEmpty();
    }

    @Test
    @DisplayName("JUnit grpc test successful for find all pets by name")
    void findAllByNamePetSuccessTest() {
        Iterator<PetOuterClass.FindAllByNameResponse> allPets = stub.findAllByName(PetOuterClass.FindAllByNamePetRequest
                .newBuilder()
                .setName("Bob")
                .build());

        ImmutableList<PetOuterClass.FindAllByNameResponse> allPetList = ImmutableList.copyOf(allPets);

        assertThat(allPetList).isNotEmpty();
    }

    @Test
    @DisplayName("JUnit grpc test unsuccessful for find all pets by name")
    void findAllByNamePetUnSuccessTest() {
        Iterator<PetOuterClass.FindAllByNameResponse> allPets = stub.findAllByName(PetOuterClass.FindAllByNamePetRequest
                .newBuilder()
                .setName("Che")
                .build());

        ImmutableList<PetOuterClass.FindAllByNameResponse> allPetList = ImmutableList.copyOf(allPets);

        assertThat(allPetList).isEmpty();
    }

    @Test
    @DisplayName("JUnit grpc test successful for delete pet by id")
    void deletePetSuccessTest() {
        PetOuterClass.DeletePetResponse expected = PetOuterClass.DeletePetResponse
                .newBuilder()
                .build();

        PetOuterClass.DeletePetResponse response = stub.deletePet(PetOuterClass.DeletePetRequest
                .newBuilder()
                .setPetId(1)
                .build());

        assertThat(response).isEqualTo(expected);
    }

    @Test
    @DisplayName("JUnit grpc test successful for delete pet by id")
    void updatePetTest() {
        PetOuterClass.UpdatePetResponse expected = PetOuterClass.UpdatePetResponse
                .newBuilder()
                .setPet(PetOuterClass.UpdatePetResponse.Pet
                        .newBuilder()
                        .setPetName("Bob")
                        .setPetType("Dog")
                        .setPetBirthDate("2010-11-03")
                        .setVaccinationName("Covid_19")
                        .setVaccinationDate("2019-12-24")
                        .build())
                .build();

        PetOuterClass.UpdatePetResponse response = stub.updatePet(PetOuterClass.UpdatePetRequest
                .newBuilder()
                .setPet(PetOuterClass.UpdatePetRequest.Pet
                        .newBuilder()
                        .setPetName("Bob")
                        .setPetType("Dog")
                        .setPetBirthDate("2010-11-03")
                        .setVaccinationName("Covid_19")
                        .setVaccinationDate("2019-12-24")
                        .build())
                .build());

        assertThat(response).isEqualTo(expected);
    }
}
