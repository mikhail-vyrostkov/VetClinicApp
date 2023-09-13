CREATE TABLE IF NOT EXISTS vetclinic.specialities
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    description varchar(255) NOT NULL,
    CONSTRAINT specialities_pkey PRIMARY KEY (id)
    )