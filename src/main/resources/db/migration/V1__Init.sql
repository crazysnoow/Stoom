DO
$do$
    BEGIN
        IF EXISTS (SELECT FROM pg_database WHERE datname = 'stoom') THEN
            RAISE NOTICE 'Database already exists';  -- optional
        ELSE
            PERFORM dblink_exec('dbname=' || current_database()  -- current db
                , 'CREATE DATABASE stoom');
        END IF;
    END
$do$;

CREATE TABLE IF NOT EXISTS public.address
(
    city character varying(100) COLLATE pg_catalog."default" NOT NULL,
    complement character varying(30) COLLATE pg_catalog."default",
    country character varying(100) COLLATE pg_catalog."default" NOT NULL,
    id serial NOT NULL,
    latitude text COLLATE pg_catalog."default",
    neighbourhood character varying(100) COLLATE pg_catalog."default" NOT NULL,
    address_number character varying(20) COLLATE pg_catalog."default" NOT NULL,
    state character varying(100) COLLATE pg_catalog."default" NOT NULL,
    street_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    zip_code character varying(25) COLLATE pg_catalog."default" NOT NULL,
    longitude text COLLATE pg_catalog."default",
    CONSTRAINT address_pkey PRIMARY KEY (id)
)