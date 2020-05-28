-- place -> continent -> country -> city

CREATE TABLE place (
    place_id   BIGINT PRIMARY KEY,
    place_name VARCHAR(150) NOT NULL
);

CREATE TABLE continent (
    continent_id       BIGINT PRIMARY KEY,
    continent_place_id BIGINT REFERENCES place(place_id) ON UPDATE CASCADE
);

CREATE TABLE country (
    country_id           BIGINT PRIMARY KEY,
    country_place_id     BIGINT REFERENCES place(place_id) ON UPDATE CASCADE,
    country_continent_id BIGINT REFERENCES continent(continent_id) ON UPDATE CASCADE
);

CREATE TABLE city (
    city_id           BIGINT PRIMARY KEY,
    city_place_id     BIGINT REFERENCES place(place_id) ON UPDATE CASCADE,
    city_country_id BIGINT REFERENCES country(country_id) ON UPDATE CASCADE
);


-- organisation -> company, university

CREATE TABLE organisation (
    organisation_id   BIGINT PRIMARY KEY,
    organisation_name VARCHAR(150) NOT NULL
);

CREATE TABLE company (
    company_id              BIGINT PRIMARY KEY,
    company_organisation_id BIGINT REFERENCES organisation(organisation_id) ON UPDATE CASCADE,
    company_country_id      BIGINT REFERENCES country(country_id) ON UPDATE CASCADE
);

CREATE TABLE university (
    university_id              BIGINT PRIMARY KEY,
    university_organisation_id BIGINT REFERENCES organisation(organisation_id) ON UPDATE CASCADE,
    university_city_id         BIGINT REFERENCES city(city_id) ON UPDATE CASCADE
);


-- tag, tag_class -> is_subclass_of, has_type

CREATE TABLE tag (
    tag_id   BIGINT PRIMARY KEY,
    tag_name VARCHAR(150) NOT NULL
);

CREATE TABLE tag_class (
    tag_class_id   BIGINT PRIMARY KEY,
    tag_class_name VARCHAR(150) NOT NULL
);

CREATE TABLE is_subclass_of (
    tag_class_id      BIGINT REFERENCES tag_class(tag_class_id),
    tag_superclass_id BIGINT REFERENCES tag_class(tag_class_id),
    CONSTRAINT is_subclass_of_pk PRIMARY KEY (tag_class_id, tag_superclass_id)
);

CREATE TABLE has_type (
    tag_id       BIGINT REFERENCES tag(tag_id),
    tag_class_id BIGINT REFERENCES tag_class(tag_class_id),
    CONSTRAINT has_type_pk PRIMARY KEY (tag_id, tag_class_id)
);

-- -- person -> knows, study_at, work_at, has_interest


CREATE TABLE person (
    person_id            BIGINT PRIMARY KEY,
    person_city_id       BIGINT REFERENCES city(city_id) ON UPDATE CASCADE ON DELETE SET NULL NOT NULL,
    person_creation_date TIMESTAMP    NOT NULL,
    person_first_name    VARCHAR(50)  NOT NULL,
    person_last_name     VARCHAR(50)  NOT NULL,
    person_gender        VARCHAR(10)  NOT NULL,
    person_birthday      DATE         NOT NULL,
    person_browser_used  VARCHAR(50)  NOT NULL,
    person_location_ip   CIDR         NOT NULL
);

CREATE TABLE person_email(
	id SERIAL PRIMARY KEY,
	email_person_id BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE SET NULL,
	email VARCHAR(150) NULL
);

CREATE TABLE person_speaks(
	id SERIAL PRIMARY KEY,
	language_person_id BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE SET NULL,
	language VARCHAR(2) NULL
);

--CREATE UNIQUE INDEX person_email_nullunique ON person(person_email) WHERE person_email IS NOT NULL;

CREATE OR REPLACE FUNCTION person_validate_birthday() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
        DECLARE

        BEGIN
            IF NEW.person_birthday > now() THEN
                RAISE EXCEPTION 'Debug--> person_validate_birthday date check %, %, %', NEW.person_birthday, TG_OP, now();
            END IF;
            RETURN NEW;
        END;
    $$;

DROP TRIGGER IF EXISTS person_validate_birthday_trigger on person;
CREATE TRIGGER person_validate_birthday_trigger BEFORE INSERT OR UPDATE ON person
    FOR EACH ROW EXECUTE PROCEDURE person_validate_birthday();

	
	DROP TRIGGER IF EXISTS person_validate_email_trigger on person_email;
CREATE TRIGGER person_validate_email_trigger BEFORE INSERT OR UPDATE ON person_email
    FOR EACH ROW EXECUTE PROCEDURE person_validate_email();
	
	
CREATE OR REPLACE FUNCTION person_validate_email() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
        BEGIN
            IF NEW.email IS NOT NULL THEN
                    IF email NOT SIMILAR TO '[A-Za-z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}' THEN
                        RAISE EXCEPTION 'Debug--> person_validate_email regex check %, %', TG_OP, email;
                    END IF;
            END IF;
            RETURN NEW;
        END;
    $$;

CREATE TABLE knows (
    knows_person_id       BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    knows_other_person_id BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    knows_creation_date   TIMESTAMP NOT NULL,
    CONSTRAINT knows_pk PRIMARY KEY (knows_person_id, knows_other_person_id)
);

CREATE TABLE study_at (
    study_at_person_id     BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    study_at_university_id BIGINT REFERENCES university(university_id) ON UPDATE CASCADE ON DELETE CASCADE,
    study_at_class_year    INT NOT NULL,
    CONSTRAINT study_at_pk PRIMARY KEY (study_at_person_id, study_at_university_id, study_at_class_year)
);

CREATE TABLE work_at (
    work_at_person_id  BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    work_at_company_id BIGINT REFERENCES company(company_id) ON UPDATE CASCADE ON DELETE CASCADE,
    work_at_work_from  INT NOT NULL,
    CONSTRAINT work_at_pk PRIMARY KEY (work_at_person_id, work_at_company_id, work_at_work_from)
);

CREATE TABLE has_interest (
    has_interest_person_id BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    has_interest_tag_id    BIGINT REFERENCES tag(tag_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT has_interest_pk PRIMARY KEY (has_interest_person_id, has_interest_tag_id)
);


-- -- forum -> has_member, forum_has_tag

CREATE TABLE forum (
    forum_id                  BIGINT PRIMARY KEY,
    forum_moderator_person_id BIGINT REFERENCES person(person_id) ON UPDATE CASCADE,
    forum_title               VARCHAR(50) NOT NULL,
    forum_creation_date TIMESTAMP NOT NULL
);

CREATE TABLE has_member (
    has_member_forum_id  BIGINT REFERENCES forum(forum_id) ON UPDATE CASCADE  ON DELETE CASCADE,
    has_member_person_id BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    has_member_join_date DATE NOT NULL,
    CONSTRAINT has_member_pk PRIMARY KEY (has_member_forum_id, has_member_person_id)
);

CREATE TABLE forum_has_tag (
    forum_has_tag_forum_id BIGINT REFERENCES forum(forum_id) ON UPDATE CASCADE ON DELETE CASCADE,
    forum_has_tag_tag_id   BIGINT REFERENCES tag(tag_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT forum_has_tag_pk PRIMARY KEY (forum_has_tag_forum_id, forum_has_tag_tag_id)
);


-- -- message -> post, comment -> message_has_tag, likes

CREATE TABLE message (
    message_id            BIGINT PRIMARY KEY,
    message_person_id     BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    message_country_id    BIGINT REFERENCES country(country_id) ON UPDATE CASCADE ON DELETE CASCADE,
    message_creation_date DATE NOT NULL,
    message_browser_used  VARCHAR(50) NOT NULL,
    message_location_ip   CIDR NOT NULL,
    message_content       VARCHAR (300),
    message_length        INT NOT NULL
);

CREATE TABLE post (
    post_id         BIGINT PRIMARY KEY,
    post_message_id BIGINT REFERENCES message(message_id) ON UPDATE CASCADE ON DELETE CASCADE,
    post_forum_id   BIGINT REFERENCES forum(forum_id)  ON UPDATE CASCADE ON DELETE CASCADE,
    post_language   VARCHAR(2) NOT NULL,
    post_image_file VARCHAR(200)
);

CREATE TABLE comment (
    comment_id                   BIGINT PRIMARY KEY,
    comment_message_id           BIGINT REFERENCES message(message_id) ON UPDATE CASCADE ON DELETE CASCADE,
    comment_commented_message_id BIGINT REFERENCES message(message_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE message_has_tag (
    message_has_tag_message_id BIGINT REFERENCES message(message_id) ON UPDATE CASCADE ON DELETE CASCADE,
    message_has_tag_tag_id     BIGINT REFERENCES tag(tag_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT message_has_tag_pk PRIMARY KEY (message_has_tag_message_id, message_has_tag_tag_id)
);

CREATE TABLE likes (
    likes_person_id     BIGINT REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE,
    likes_message_id    BIGINT REFERENCES message(message_id) ON UPDATE CASCADE ON DELETE CASCADE,
    likes_creation_date DATE NOT NULL,
    CONSTRAINT likes_pk PRIMARY KEY (likes_person_id, likes_message_id)
);