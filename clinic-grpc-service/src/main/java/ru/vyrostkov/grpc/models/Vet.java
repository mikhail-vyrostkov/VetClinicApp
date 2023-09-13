package ru.vyrostkov.grpc.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vats", schema = "vetclinic")
public class Vet extends Person {
    @Builder
    public Vet(Long id, String firstName, String lastName, String middleName, int age,
               Set<Speciality> specialities, Set<Visit> visits, Set<Booking> bookings) {
        super(id, firstName, lastName, middleName, age);

        if (specialities == null || specialities.size() > 0) {
            this.specialities = specialities;
        }
        if (visits == null || visits.size() > 0 ) {
            this.visits = visits;
        }
        if (bookings == null || bookings.size() > 0 ) {
            this.bookings = bookings;
        }
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vets_specialities", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vet", fetch = FetchType.LAZY)
    private Set<Visit> visits = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vet", fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vets_duty_schedules", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "duty_schedule_id"))
    private Set<DutySchedule> dutySchedules = new HashSet<>();
}
