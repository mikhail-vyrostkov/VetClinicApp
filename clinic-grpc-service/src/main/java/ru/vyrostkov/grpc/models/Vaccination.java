package ru.vyrostkov.grpc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vaccinations", schema = "vetclinic")
public class Vaccination extends BaseEntity {

    @Builder
    public Vaccination(Long id, String name, LocalDate vaccinationDate) {
        super(id);
        this.name = name;
        this.vaccinationDate = vaccinationDate;
    }

    @Column(name = "name")
    private String name;
    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

}
