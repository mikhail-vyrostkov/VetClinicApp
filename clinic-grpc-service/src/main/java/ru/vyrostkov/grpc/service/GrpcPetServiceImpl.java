package ru.vyrostkov.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import ru.vyrostkov.grpc.dto.PetRequestDTO;
import ru.vyrostkov.grpc.dto.PetResponseDTO;
import ru.vyrostkov.grpc.service.PetService;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetOuterClass;
import ru.vyrostkov.grpc.server.grpc_server.pet.PetServiceGrpc;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class GrpcPetServiceImpl extends PetServiceGrpc.PetServiceImplBase {

    private PetService petService;

    @Override
    public void createPet(PetOuterClass.CreatePetRequest request,
                          StreamObserver<PetOuterClass.CreatePetResponse> responseObserver) {

        PetRequestDTO petRequestDTO = PetRequestDTO.builder()
                .petId(request.getPet().getPetId())
                .petName(request.getPet().getPetName())
                .petType(request.getPet().getPetType())
                .petBirthDate(request.getPet().getPetBirthDate())
                .vaccinationDate(request.getPet().getVaccinationDate())
                .vaccinationName(request.getPet().getVaccinationName())
                .ownerFirstName(request.getPet().getOwnerFirstName())
                .ownerLastName(request.getPet().getOwnerLastName())
                .ownerMiddleName(request.getPet().getOwnerMiddleName())
                .ownerAddress(request.getPet().getOwnerAddress())
                .ownerAge(request.getPet().getOwnerAge())
                .ownerTelephone(request.getPet().getOwnerTelephone())
                .build();


        PetOuterClass.CreatePetResponse response = PetOuterClass.CreatePetResponse
                .newBuilder()
                .setPetId(petService.createPet(petRequestDTO))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findByIDPet(PetOuterClass.FindByIdPetRequest request,
                            StreamObserver<PetOuterClass.FindByIdPetResponse> responseObserver) {

        PetResponseDTO pet = petService.findByIDPet(request.getPetId());

        PetOuterClass.FindByIdPetResponse response = PetOuterClass.FindByIdPetResponse
                .newBuilder()
                .setPet(PetOuterClass.FindByIdPetResponse.Pet
                        .newBuilder()
                        .setPetName(pet.getPetName())
                        .setPetType(pet.getPetType())
                        .setPetBirthDate(pet.getPetBirthDate())
                        .setOwnerFirstName(pet.getOwnerFirstName())
                        .setOwnerLastName(pet.getOwnerLastName())
                        .setOwnerMiddleName(pet.getOwnerMiddleName())
                        .setVaccinationDate(pet.getVaccinationDate())
                        .setVaccinationName(pet.getVaccinationName())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAllPet(PetOuterClass.FindAllRequest request,
                                      StreamObserver<PetOuterClass.FindAllResponse> responseObserver) {

        List<PetResponseDTO> petResponseDTOList = petService.findAllPet();

        PetOuterClass.FindAllResponse response = PetOuterClass.FindAllResponse
                .newBuilder()
                .addAllPet((petResponseDTOList.stream()
                        .map(p -> PetOuterClass.FindAllResponse.Pet
                                .newBuilder()
                                .setPetName(p.getPetName())
                                .setPetType(p.getPetType())
                                .setPetBirthDate(p.getPetBirthDate())
                                .setOwnerFirstName(p.getOwnerFirstName())
                                .setOwnerLastName(p.getOwnerLastName())
                                .setOwnerMiddleName(p.getOwnerMiddleName())
                                .setVaccinationDate(p.getVaccinationDate())
                                .setVaccinationName(p.getVaccinationName())
                                .build())
                        .collect(Collectors.toList())))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAllByName(PetOuterClass.FindAllByNamePetRequest request,
                               StreamObserver<PetOuterClass.FindAllByNameResponse> responseObserver) {

        List<PetResponseDTO> petResponseDTOList = petService.findAllByNamePet(request.getName());

        PetOuterClass.FindAllByNameResponse response = PetOuterClass.FindAllByNameResponse
                .newBuilder()
                .addAllPet((petResponseDTOList.stream()
                        .map(p -> PetOuterClass.FindAllByNameResponse.Pet
                                .newBuilder()
                                .setPetName(p.getPetName())
                                .setPetType(p.getPetType())
                                .setPetBirthDate(p.getPetBirthDate())
                                .setOwnerFirstName(p.getOwnerFirstName())
                                .setOwnerLastName(p.getOwnerLastName())
                                .setOwnerMiddleName(p.getOwnerMiddleName())
                                .setVaccinationDate(p.getVaccinationDate())
                                .setVaccinationName(p.getVaccinationName())
                                .build())
                        .collect(Collectors.toList())))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deletePet(PetOuterClass.DeletePetRequest request,
                          StreamObserver<PetOuterClass.DeletePetResponse> responseObserver) {

        petService.deletePet(request.getPetId());

        PetOuterClass.DeletePetResponse response = PetOuterClass.DeletePetResponse
                .newBuilder()
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updatePet(PetOuterClass.UpdatePetRequest request,
                          StreamObserver<PetOuterClass.UpdatePetResponse> responseObserver) {

        PetRequestDTO petRequestDTO = PetRequestDTO.builder()
                .petName(request.getPet().getPetName())
                .petType(request.getPet().getPetType())
                .petBirthDate(request.getPet().getPetBirthDate())
                .vaccinationDate(request.getPet().getVaccinationDate())
                .vaccinationName(request.getPet().getVaccinationName())
                .build();

        PetResponseDTO updatePet = petService.updatePet(petRequestDTO);

        PetOuterClass.UpdatePetResponse response = PetOuterClass.UpdatePetResponse
                .newBuilder()
                .setPet(PetOuterClass.UpdatePetResponse.Pet
                        .newBuilder()
                        .setPetName(updatePet.getPetName())
                        .setPetType(updatePet.getPetType())
                        .setPetBirthDate(updatePet.getPetBirthDate())
                        .setVaccinationDate(updatePet.getVaccinationDate())
                        .setVaccinationName(updatePet.getVaccinationName())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
