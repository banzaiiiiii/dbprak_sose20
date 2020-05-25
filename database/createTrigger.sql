-- validate email & birthdays

CREATE FUNCTION validateEmail() RETURNS trigger AS $validateEmail$
    BEGIN
        IF NEW.person_email NOT LIKE '%@%' THEN
			New.person_email = null;
	 END IF;
		--IF NEW.person_birthday > CURRENT_DATE THEN
	--	New.person_email = null;
		--END IF;	
		RETURN NEW;
    END;
	
$validateEmail$ LANGUAGE plpgsql;

CREATE TRIGGER validateEmail BEFORE INSERT OR UPDATE ON person
    FOR EACH ROW EXECUTE FUNCTION validateEmail();




