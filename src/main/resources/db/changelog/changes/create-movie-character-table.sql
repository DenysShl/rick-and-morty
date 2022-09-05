--liquibase formatted sql
--changeset <shd>:<create-movie_character_table>
CREATE TABLE IF NOT EXISTS public.movie_character
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    gender character varying(255) NOT NULL,
    CONSTRAINT movie_character_pk PRIMARY KEY (id)
);
--rollback DROP TABLE movie_character;