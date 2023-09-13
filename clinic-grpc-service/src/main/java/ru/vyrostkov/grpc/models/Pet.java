package ru.vyrostkov.grpc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pets", schema = "vetclinic")
public class Pet extends BaseEntity{
    @Builder
    public Pet(Long id, String name, PetType petType, Vaccination vaccination, Owner owner,
               LocalDate birthDate, Set<Visit> visits, Set<Booking> bookings) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.vaccination = vaccination;
        this.owner = owner;
        this.birthDate = birthDate;

        if (visits == null || visits.size() > 0 ) {
            this.visits = visits;
        }
        if (bookings == null || bookings.size() > 0 ) {
            this.bookings = bookings;
        }
    }

    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private PetType petType;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.LAZY)
    private Set<Visit> visits = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();
}
