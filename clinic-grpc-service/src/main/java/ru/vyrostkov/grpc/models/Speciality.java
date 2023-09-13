package ru.vyrostkov.grpc.models;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialities", schema = "vetclinic")
public class Speciality extends BaseEntity {
    @Column(name = "description")
    private String description;
}
