CREATE TABLE IF NOT EXISTS vetclinic.vets_duty_schedules
(
    vet_id bigint NOT NULL,
    duty_schedules_id bigint NOT NULL,
    CONSTRAINT vets_duty_schedules_pkey PRIMARY KEY (vet_id, duty_schedules_id),
    CONSTRAINT duty_schedules FOREIGN KEY (duty_schedules_id)
    REFERENCES vetclinic.duty_schedules (id),
    CONSTRAINT vets FOREIGN KEY (vet_id)
    REFERENCES vetclinic.vets (id)
    )