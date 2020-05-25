-- validate email & birthdays

CREATE OR REPLACE FUNCTION validateEmail() RETURNS trigger AS $validateEmail$
    BEGIN
        IF NEW.person_email NOT LIKE '%_@%_.__%' THEN
			RAISE EXCEPTION 'email not valide';
		END IF;
		IF NEW.person_birthday > CURRENT_DATE THEN
			RAISE EXCEPTION 'birthday is in future';
		END IF;
		
		Return NEW;
    END;
$validateEmail$ LANGUAGE plpgsql;

CREATE TRIGGER validateEmail BEFORE INSERT OR UPDATE ON person
    FOR EACH ROW EXECUTE FUNCTION validateEmail();




