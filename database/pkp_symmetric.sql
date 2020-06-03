CREATE OR REPLACE VIEW pkp_symmetric AS 
	SELECT knows_person_id, knows_other_person_id
	FROM knows 

	UNION ALL
	
	SELECT knows_other_person_id, knows_person_id
	FROM knows;