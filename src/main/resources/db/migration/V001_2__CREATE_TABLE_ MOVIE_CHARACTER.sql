CREATE TABLE IF NOT EXISTS public.movie_character
(
    id bigint PRIMARY KEY NOT NULL,
    name character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    gender character varying(255) NOT NULL
);
