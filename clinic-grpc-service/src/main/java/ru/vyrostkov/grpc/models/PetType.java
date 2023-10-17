package ru.vyrostkov.grpc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types", schema = "vetclinic")
public class PetType extends BaseEntity {

    @Builder
    public PetType(int id, String name) {
        super(id);
        this.name = name;
    }
    @Column(name = "name")
    private String name;
}
