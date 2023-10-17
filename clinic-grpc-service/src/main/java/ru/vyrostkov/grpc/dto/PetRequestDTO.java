package ru.vyrostkov.grpc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PetRequestDTO(
        @JsonProperty("pet_name") String petName,
        @JsonProperty("pet_type") String petType,
        @JsonProperty("pet_birth_date") String petBirthDate
){}
