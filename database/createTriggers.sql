/*
	Wenn Arbeitsverhältnis in work_at gelöscht wird
	Wird Trigger ausgelöst:
	Trigger erstellt neue tabelle person_work_delation_history
	Neue TAbelle mit folgenden Spalten: work_at_person_id, work_at_company_id, work_at_work_from, delation_timestamp
	
	TEST SQL FOR DELATION:
	
	DELETE FROM work_at
	WHERE work_at_person_id = 12094627905604 AND work_at_company_id = 897;
*/
DROP TRIGGER IF EXISTS delete_person_work_trigger on work_at;
CREATE TRIGGER delete_person_work_trigger AFTER DELETE ON work_at
    FOR EACH ROW EXECUTE PROCEDURE delete_person_work();

CREATE OR REPLACE FUNCTION delete_person_work() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
        DECLARE
        BEGIN
		
			CREATE TABLE IF NOT EXISTS person_work_delation_history (
				work_at_person_id  BIGINT PRIMARY KEY,
				work_at_company_id BIGINT,
				work_at_work_from  INT NOT NULL,
				delation_timestamp TIMESTAMP NOT NULL
			);
			
			INSERT INTO person_work_delation_history SELECT OLD.work_at_person_id, OLD.work_at_company_id, OLD.work_at_work_from, now();
			RETURN OLD;
        END;
    $$;

/*
	Trigger that validates that a persons birthday is not in the future
*/
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

/*
	Trigger that checks if a persons email is valid 
*/
	
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
	