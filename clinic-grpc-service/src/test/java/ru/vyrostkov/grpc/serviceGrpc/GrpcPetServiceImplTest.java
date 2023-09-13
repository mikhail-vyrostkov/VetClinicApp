package ru.vyrostkov.grpc.serviceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.vyrostkov.grpc.config.GrpcPetServiceTestConfiguration;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetOuterClass;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetServiceGrpc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test", // Enable inProcess server
        "grpc.server.port=9092", // Disable external server
        "grpc.client.inProcess.address=in-process:test" // Configure the client to connect to the inProcess server
})
@SpringJUnitConfig(classes = { GrpcPetServiceTestConfiguration.class })
@DirtiesContext
public class GrpcPetServiceImplTest {

    @GrpcClient("inProcess")
    private PetServiceGrpc.PetServiceBlockingStub petServiceBlockingStub;

    @Test
    @DisplayName("JUnit grpc test for create pet")
    void createPetTest() {
        PetOuterClass.CreatePetRequest createPetRequest = PetOuterClass.CreatePetRequest
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
                .build();

        PetOuterClass.CreatePetResponse createPetResponse = petServiceBlockingStub.createPet(createPetRequest);

        assertNotNull(createPetResponse);
        assertEquals(1, createPetResponse.getPetId());

    }



//    @InjectMocks
//    private GrpcPetServiceImpl grpcPetService;
//    @Mock
//    private PetRepository petRepository;
//    private PetOuterClass.CreatePetRequest createPetRequest;
//    private PetOuterClass.CreatePetResponse createPetResponse;

//    @BeforeEach
//    public void setUp() throws Exception {
//        createPetRequest = PetOuterClass.CreatePetRequest
//                .newBuilder()
//                .setPet(PetOuterClass.CreatePetRequest.Pet
//                        .newBuilder()
//                        .setPetId(1)
//                        .setPetName("Bob")
//                        .setPetType("Dog")
//                        .setPetBirthDate("2010-11-03")
//                        .setOwnerLastName("Vyrostkov")
//                        .setOwnerFirstName("Mikhail")
//                        .setOwnerMiddleName("Yurevich")
//                        .setOwnerAge(28)
//                        .setOwnerAddress("Moscow")
//                        .setOwnerTelephone("89258127349")
//                        .setVaccinationName("Covid_19")
//                        .setVaccinationDate("2019-12-24")
//                        .build())
//                .build();
//
//        createPetResponse = PetOuterClass.CreatePetResponse
//                .newBuilder()
//                .setPetId(1)
//                .build();
//    }

//    @Test
//    @DisplayName("JUnit grpc test for create pet")
//    public void createPetTest() {
//
//        when(grpcPetService.createPet(any(PetOuterClass.CreatePetRequest.class),
//                any(StreamObserver.class)))
//                .thenReturn(createPetResponse);
//
//        StreamObserver<PetOuterClass.CreatePetResponse> streamObserver = mock(StreamObserver.class);
//
//        grpcPetService.createPet(createPetRequest, streamObserver);
//
//        verify(streamObserver).onNext(createPetResponse);
//        verify(streamObserver).onCompleted();
//
//    }






//    @Test
//    @DisplayName("JUnit grpc test for create pet")
//    public void createPetTest() {

////        PetOuterClass.CreatePetResponse expected = PetOuterClass.CreatePetResponse
//                .newBuilder()
//                .setPetId(1)
//                .build();
//        PetOuterClass.CreatePetResponse response = stub.createPet(PetOuterClass.CreatePetRequest
//                .newBuilder()
//                .setPet(PetOuterClass.CreatePetRequest.Pet
//                        .newBuilder()
//                        .setPetId(1)
//                        .setPetName("Bob")
//                        .setPetType("Dog")
//                        .setPetBirthDate("2010-11-03")
//                        .setOwnerLastName("Vyrostkov")
//                        .setOwnerFirstName("Mikhail")
//                        .setOwnerMiddleName("Yurevich")
//                        .setOwnerAge(28)
//                        .setOwnerAddress("Moscow")
//                        .setOwnerTelephone("89258127349")
//                        .setVaccinationName("Covid_19")
//                        .setVaccinationDate("2019-12-24")
//                        .build())
//                .build());
//
//        assertThat(response).isEqualTo(expected);
//    }

//    @Test
//    @DisplayName("JUnit grpc test for find pet by id")
//    public void findByIDPetTest() {
//        PetOuterClass.FindByIdPetResponse expected = PetOuterClass.FindByIdPetResponse
//                .newBuilder()
//                .setPet(PetOuterClass.FindByIdPetResponse.Pet
//                        .newBuilder()
//                        .setPetName("Bob")
//                        .setPetType("Dog")
//                        .setPetBirthDate("2010-11-03")
//                        .setOwnerLastName("Vyrostkov")
//                        .setOwnerFirstName("Mikhail")
//                        .setOwnerMiddleName("Yurevich")
//                        .setVaccinationName("Covid_19")
//                        .setVaccinationDate("2019-12-24")
//                        .build())
//                .build();
//
//        PetOuterClass.FindByIdPetResponse response = stub.findByIDPet(PetOuterClass.FindByIdPetRequest
//                .newBuilder()
//                .setPetId(1)
//                .build());
//
//        assertThat(response).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("JUnit grpc test for find all pets")
//    public void findAllPetTest() {
//        Iterator<PetOuterClass.FindAllResponse> allPets = stub.findAllPet(PetOuterClass.FindAllRequest
//                .newBuilder()
//                .build());
//
//        ImmutableList<PetOuterClass.FindAllResponse> allPetList = ImmutableList.copyOf(allPets);
//
//        assertThat(allPetList).isNotEmpty();
//    }
//
//    @Test
//    @DisplayName("JUnit grpc test successful for find all pets by name")
//    public void findAllByNamePetSuccessTest() {
//        Iterator<PetOuterClass.FindAllByNameResponse> allPets = stub.findAllByName(PetOuterClass.FindAllByNamePetRequest
//                .newBuilder()
//                .setName("Bob")
//                .build());
//
//        ImmutableList<PetOuterClass.FindAllByNameResponse> allPetList = ImmutableList.copyOf(allPets);
//
//        assertThat(allPetList).isNotEmpty();
//    }
//
//    @Test
//    @DisplayName("JUnit grpc test unsuccessful for find all pets by name")
//    public void findAllByNamePetUnSuccessTest() {
//        Iterator<PetOuterClass.FindAllByNameResponse> allPets = stub.findAllByName(PetOuterClass.FindAllByNamePetRequest
//                .newBuilder()
//                .setName("Che")
//                .build());
//
//        ImmutableList<PetOuterClass.FindAllByNameResponse> allPetList = ImmutableList.copyOf(allPets);
//
//        assertThat(allPetList).isEmpty();
//    }
//
//    @Test
//    @DisplayName("JUnit grpc test successful for delete pet by id")
//    public void deletePetSuccessTest() {
//        PetOuterClass.DeletePetResponse expected = PetOuterClass.DeletePetResponse
//                .newBuilder()
//                .build();
//
//        PetOuterClass.DeletePetResponse response = stub.deletePet(PetOuterClass.DeletePetRequest
//                .newBuilder()
//                .setPetId(1)
//                .build());
//
//        assertThat(response).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("JUnit grpc test successful for delete pet by id")
//    public void updatePetTest() {
//        PetOuterClass.UpdatePetResponse expected = PetOuterClass.UpdatePetResponse
//                .newBuilder()
//                .setPet(PetOuterClass.UpdatePetResponse.Pet
//                        .newBuilder()
//                        .setPetName("Bob")
//                        .setPetType("Dog")
//                        .setPetBirthDate("2010-11-03")
//                        .setVaccinationName("Covid_19")
//                        .setVaccinationDate("2019-12-24")
//                        .build())
//                .build();
//
//        PetOuterClass.UpdatePetResponse response = stub.updatePet(PetOuterClass.UpdatePetRequest
//                .newBuilder()
//                .setPet(PetOuterClass.UpdatePetRequest.Pet
//                        .newBuilder()
//                        .setPetName("Bob")
//                        .setPetType("Dog")
//                        .setPetBirthDate("2010-11-03")
//                        .setVaccinationName("Covid_19")
//                        .setVaccinationDate("2019-12-24")
//                        .build())
//                .build());
//
//        assertThat(response).isEqualTo(expected);
//    }
}
