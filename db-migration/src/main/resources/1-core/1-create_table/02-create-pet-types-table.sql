CREATE TABLE IF NOT EXISTS vetclinic.types
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name varchar(200) NOT NULL,
    CONSTRAINT types_pkey PRIMARY KEY (id)
    )