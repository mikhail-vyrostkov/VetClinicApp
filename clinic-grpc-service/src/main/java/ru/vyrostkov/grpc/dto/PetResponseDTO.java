package ru.vyrostkov.grpc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetResponseDTO {

    @JsonProperty("pet_ID")
    private long petId;
    @JsonProperty("pet_name")
    private String petName;
    @JsonProperty("pet_type")
    private String petType;
    @JsonProperty("vaccination_name")
    private String vaccinationName;
    @JsonProperty("vaccination_date")
    private String vaccinationDate;
    @JsonProperty("owner_last_name")
    private String ownerLastName;
    @JsonProperty("owner_middle_name")
    private String ownerMiddleName;
    @JsonProperty("owner_first_name")
    private String ownerFirstName;
    @JsonProperty("pet_birth_date")
    private String petBirthDate;

}