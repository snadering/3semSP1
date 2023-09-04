-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    name character varying NOT NULL,
    surname character varying NOT NULL,
    phone_number character varying NOT NULL,
    email character varying NOT NULL,
    address integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.zipcode
(
    zip integer NOT NULL,
    region character varying NOT NULL,
    city character varying NOT NULL,
    municipality character varying NOT NULL,
    PRIMARY KEY (zip)
);

CREATE TABLE IF NOT EXISTS public.hobby
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name character varying NOT NULL,
    category character varying NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.hobby_users
(
    hobby_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    users_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 )
);

CREATE TABLE IF NOT EXISTS public.event
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name character varying NOT NULL,
    address integer NOT NULL,
    price numeric NOT NULL,
    hobby integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.event_users
(
    event_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    users_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 )
);

CREATE TABLE IF NOT EXISTS public.address
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    street character varying NOT NULL,
    "number" character varying NOT NULL,
    zip integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.users
    ADD FOREIGN KEY (address)
    REFERENCES public.address (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.hobby_users
    ADD FOREIGN KEY (hobby_id)
    REFERENCES public.hobby (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.hobby_users
    ADD FOREIGN KEY (users_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.event
    ADD FOREIGN KEY (hobby)
    REFERENCES public.hobby (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.event
    ADD FOREIGN KEY (address)
    REFERENCES public.address (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.event_users
    ADD FOREIGN KEY (event_id)
    REFERENCES public.event (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.event_users
    ADD FOREIGN KEY (users_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.address
    ADD FOREIGN KEY (zip)
    REFERENCES public.zipcode (zip) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;
