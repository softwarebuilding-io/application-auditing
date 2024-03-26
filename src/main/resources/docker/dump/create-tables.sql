SET SCHEMA 'public';

BEGIN;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE IF EXISTS public.title_genre
    DROP CONSTRAINT IF EXISTS fk_genre_title;

ALTER TABLE IF EXISTS public.title_genre
    DROP CONSTRAINT IF EXISTS fk_title_genre;

DROP TABLE IF EXISTS public.genre CASCADE;

DROP TABLE IF EXISTS public.title CASCADE;

DROP TABLE IF EXISTS public.title_genre CASCADE;

CREATE TABLE public.genre
(
    id         UUID                        NOT NULL DEFAULT uuid_generate_v4(),
    name       VARCHAR(255)                NOT NULL UNIQUE,
    created_ip VARCHAR(15) NOT NULL DEFAULT '127.0.0.1',
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_user VARCHAR(60) NOT NULL DEFAULT 'ADMIN',
    updated_ip VARCHAR(15),
    updated_on TIMESTAMP,
    updated_user VARCHAR(60),
    PRIMARY KEY (id)
);

CREATE TABLE public.title
(
    id           UUID                        NOT NULL DEFAULT uuid_generate_v4(),
    name         VARCHAR(255)                NOT NULL UNIQUE,
    description  VARCHAR(255)                NOT NULL,
    release_year SMALLINT                    NOT NULL,
    type         INTEGER                     NOT NULL,
    created_ip VARCHAR(15) NOT NULL DEFAULT '127.0.0.1',
    created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_user VARCHAR(60) NOT NULL DEFAULT 'ADMIN',
    updated_ip VARCHAR(15),
    updated_on TIMESTAMP,
    updated_user VARCHAR(60),
    PRIMARY KEY (id)
);

CREATE TABLE public.title_genre
(
    genre_id UUID NOT NULL,
    title_id UUID NOT NULL,
    PRIMARY KEY (genre_id, title_id)
);

ALTER TABLE IF EXISTS public.title_genre
    ADD CONSTRAINT fk_genre_title
        FOREIGN KEY (genre_id)
            REFERENCES public.genre
            ON DELETE CASCADE;

ALTER TABLE IF EXISTS public.title_genre
    ADD CONSTRAINT fk_title_genre
        FOREIGN KEY (title_id)
            REFERENCES public.title;

END;