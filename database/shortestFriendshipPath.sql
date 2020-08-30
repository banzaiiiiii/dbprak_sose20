DROP FUNCTION pkp_sp(BIGINT);
CREATE OR REPLACE FUNCTION pkp_SP(personID BIGINT)
RETURNS TABLE(
            person_id BIGINT,
            person_city_id BIGINT,
            person_creation_date TIMESTAMP,
            person_first_name VARCHAR(50),
            person_last_name VARCHAR(50),
            person_gender VARCHAR(10),
            person_birthday DATE,
            person_browser_used VARCHAR(50),
            person_location_ip CIDR) AS
$$
BEGIN
    return query
    SELECT
            p.person_id,
            p.person_city_id,
            p.person_creation_date,
            p.person_first_name,
            p.person_last_name,
            p.person_gender,
            p.person_birthday,
            p.person_browser_used,
            p.person_location_ip
    FROM pkp_symmetric pkp
        JOIN Person p ON pkp.knows_other_person_id=p.person_id
    WHERE knows_person_id=personID;
END;
$$
LANGUAGE 'plpgsql' ;
