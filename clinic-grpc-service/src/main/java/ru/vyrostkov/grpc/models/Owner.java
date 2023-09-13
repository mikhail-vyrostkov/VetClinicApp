package ru.vyrostkov.grpc.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "owners", schema = "vetclinic")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String middleName, int age, String address,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName, middleName, age);
        this.address = address;
        this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Pet> pets = new HashSet<>();
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
}
