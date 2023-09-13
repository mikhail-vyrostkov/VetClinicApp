CREATE TABLE IF NOT EXISTS vetclinic.vets_specialities
(
    vet_id bigint NOT NULL,
    speciality_id bigint NOT NULL,
    CONSTRAINT vets_specialities_pkey PRIMARY KEY (vet_id, speciality_id),
    CONSTRAINT specialities FOREIGN KEY (speciality_id)
    REFERENCES vetclinic.specialities (id),
    CONSTRAINT vets FOREIGN KEY (vet_id)
    REFERENCES vetclinic.vets (id)
    )