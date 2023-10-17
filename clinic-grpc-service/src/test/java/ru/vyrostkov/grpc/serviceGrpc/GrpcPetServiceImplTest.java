package ru.vyrostkov.grpc.serviceGrpc;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.vyrostkov.grpc.GrpcApplication;
import ru.vyrostkov.grpc.dto.PetRequestDTO;
import ru.vyrostkov.grpc.dto.PetResponseDTO;
import ru.vyrostkov.grpc.models.Pet;
import ru.vyrostkov.grpc.models.PetType;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetOuterClass;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetServiceGrpc;
import ru.vyrostkov.grpc.service.PetService;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test",
        "grpc.server.port=9092",
        "grpc.client.petService.address=in-process:test"
})
@SpringJUnitConfig(classes = {GrpcApplication.class})
@Log4j2
public class GrpcPetServiceImplTest {

    @MockBean
    PetService petService;
    @GrpcClient("petService")
    private PetServiceGrpc.PetServiceBlockingStub petServiceBlockingStub;
    final int petId = 1;
    Pet pet;
    PetRequestDTO petRequestDTO;
    PetResponseDTO petResponseDTO;

    @BeforeEach
    void setUp() {
        pet = Pet
                .builder()
                .name("Bob")
                .petType(PetType.builder().name("dog").build())
                .birthDate(LocalDate.parse("2020-12-11"))
                .build();

        petRequestDTO = PetRequestDTO
                .builder()
                .petName("Bob")
                .petType("dog")
                .petBirthDate("2020-12-11")
                .build();

        petResponseDTO = PetResponseDTO
                .builder()
                .petName("Bob")
                .petType("dog")
                .build();
    }

    @Test
    @DisplayName("JUnit grpc test for create pet")
    public void createPetTest() {

        doReturn(1)
                .when(petService)
                .createPet(any());

        PetOuterClass.CreatePetRequest request = PetOuterClass.CreatePetRequest
                .newBuilder()
                .setPet(PetOuterClass.CreatePetRequest.Pet
                        .newBuilder()
                        .setPetName("Bob")
                        .setPetType("dog")
                        .setPetBirthDate("2020-12-11")
                        .build())
                .build();

        PetOuterClass.CreatePetResponse createPetResponse =
                petServiceBlockingStub.createPet(request);

        assertThat(createPetResponse).isNotNull();
        assertThat(petId).isEqualTo(createPetResponse.getPetId());

        verify(petService).createPet(petRequestDTO);
    }

    @Test
    @DisplayName("JUnit grpc test for find pet by id")
    public void findByIDPetTest() {

        doReturn(petResponseDTO)
                .when(petService)
                .findByIDPet(anyInt());

        PetOuterClass.FindByIdPetRequest request =
                PetOuterClass.FindByIdPetRequest
                        .newBuilder()
                        .setPetId(petId)
                        .build();

        PetOuterClass.FindByIdPetResponse response =
                petServiceBlockingStub.findByIDPet(request);

        assertThat(response).isNotNull();
        assertThat(response.getPet().getPetName()).isEqualTo(pet.getName());
        verify(petService).findByIDPet(petId);
    }

    @Test
    @DisplayName("JUnit grpc test for find pet by id when pet not found")
    public void PetNotFoundWhenFindByIDTest() throws Exception {

        doReturn(null)
                .when(petService)
                .findByIDPet(anyInt());

        PetOuterClass.FindByIdPetRequest request =
                PetOuterClass.FindByIdPetRequest
                        .newBuilder()
                        .setPetId(petId)
                        .build();

        StatusRuntimeException thrown =
                Assertions.assertThrows(StatusRuntimeException.class, () ->
                        petServiceBlockingStub.findByIDPet(request));

        assertThat(thrown.getStatus().getCode().toString())
                .isEqualTo("NOT_FOUND");
        assertThat(thrown.getMessage())
                .isEqualTo("NOT_FOUND: This pet with id = 1 is not found");
        Metadata metadata = Status.trailersFromThrowable(thrown);
        PetOuterClass.ErrorResponse errorResponse =
                metadata.get(ProtoUtils.keyForProto(
                                PetOuterClass.ErrorResponse.getDefaultInstance()
                        )
                );
        assertThat(errorResponse.getErrorName())
                .isEqualTo("This pet with id = 1 is not in the database");

        verify(petService).findByIDPet(petId);
    }
}
