package ru.vyrostkov.grpc.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vyrostkov.grpc.enumUnit.DaysOfWeek;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bookings", schema = "vetclinic")
public class Booking extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "day_of_week")
    private DaysOfWeek dayOfWeek;
    @Column(name = "date")
    private LocalDate date;
}
