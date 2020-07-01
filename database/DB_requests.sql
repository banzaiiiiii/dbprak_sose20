-- 1. In wie vielen verschiedenen afrikanischen Städten gibt es eine Universität?
-------------
-- Records: 100
-------------
SELECT count(DISTINCT c.city_id)
FROM university u
     JOIN city c ON u.university_city_id = c.city_id
     JOIN country c2 ON c.city_country_id = c2.country_id
     JOIN continent c3 ON c3.continent_id = c2.country_continent_id
     JOIN place p ON p.place_id = c3.continent_place_id
WHERE p.place_name = 'Africa';

-- 2. Wie viele Forenbeiträge (Posts) hat die älteste Person verfasst (Ausgabe: Name, #Forenbeiträge)?
-------------
-- Records: 1
-------------
-- Masahiro          | Sato             |     1
SELECT p.person_first_name, p.person_last_name, count(po.post_id)
FROM message AS m
    JOIN post AS po on po.post_message_id=m.message_id
    JOIN person AS p on p.person_id=m.message_person_id
GROUP BY p.person_birthday, p.person_last_name, p.person_first_name
ORDER BY p.person_birthday ASC
limit 1;

--3. Wie viele Kommentare zu Posts gibt es aus jedem Land (Ausgabe aufsteigend sortiert nach Kommentaranzahl)?
-------------
-- Records: 111
-------------
SET CLIENT_ENCODING TO 'utf8';

SELECT pl.place_name AS name, count(comments_on_posts.comment_id) AS count
FROM country cou LEFT JOIN
     (
         SELECT c.comment_id AS comment_id, m.message_country_id AS country_id
         FROM comment c
              JOIN message m ON c.comment_message_id = m.message_id
              JOIN message m2 ON c.comment_commented_message_id = m2.message_id
              JOIN post p ON m2.message_id = p.post_message_id
     ) AS comments_on_posts ON cou.country_id = comments_on_posts.country_id
     JOIN place pl ON pl.place_id=cou.country_place_id
GROUP BY name ORDER BY count, name;

--4. Aus welchen Städten stammen die meisten Nutzer (Ausgabe Name + Einwohnerzahl)?
-- Solution:
-- Ludwigsburg | 2
-- Rahim Yar Khan | 2
SET CLIENT_ENCODING TO 'utf8';

SELECT pl.place_name, COUNT(p.person_id)
FROM city AS c
    JOIN person AS p ON p.person_city_id=c.city_id
    JOIN place AS pl on pl.place_id=c.city_place_id
GROUP BY pl.place_name
ORDER BY COUNT(p.person_id) DESC;

--5. Mit wem ist ‘Hans Johansson’ befreundet?
-------------
-- Records: 12
-------------
-- Abdoulaye Khouma Dia
-- Ali Achiou
-- Alim Guliyev
-- Bryn Davies
-- Eric Mettacara
-- Hossein Forouhar
-- Jan Zakrzewski
-- Jorge Araujo Castro
-- Karl Fischer
-- Otto Richter
-- Paul Becker
-- Wojciech Ciesla
-------------
SELECT concat(p2.person_first_name, ' ', p2.person_last_name) AS name
FROM pkp_symmetric
     JOIN person p1 ON pkp_symmetric.knows_person_id = p1.person_id
     JOIN person p2 ON pkp_symmetric.knows_other_person_id = p2.person_id
WHERE concat(p1.person_first_name, ' ', p1.person_last_name) = 'Hans Johansson'
ORDER BY name;

--6. Wer sind die “echten” Freundesfreunde von ‘Hans Johansson’? “Echte” Freundesfreunde dürfen nicht gleichzeitig direkte Freunde von ‘Hans Johansson’ sein. Sortieren Sie die Ausgabe alphabetisch nach dem Nachnamen.
-------------
-- Records: 48
-------------
-- Wei                 | Hu
-- Lin                 | Zhang
-- Francisco           | Reyes
-- Tamas               | Gabor
-- Baby                | Yang
-- Cheng               | Chen
-- Chong               | Liu
-- Jimmy               | Burak
-- Ali                 | Abouba
-- Akira               | Yamamoto
-- Jie                 | Li
-- Chen                | Yang
-- Jun                 | Hu
-- Evangelos           | Alkaios
-- Amy                 | Chen
-- Djelaludin          | Zaland
-- Alexei              | Kahnovich
-- Alec                | Lin
-- Roberto             | Diaz
-- Jie                 | Yang
-- Yahya Ould Ahmed El | Abdallahi
-- Anson               | Chen
-- Babar               | Khan
-- Lei                 | Zhang
-- Joakim              | Larsson
-- Alexei              | Codreanu
-- Abdul Haris         | Tobing
-- Yang                | Wang
-- Otto                | Redl
-- Li                  | Zhang
-- Tissa               | Perera
-- Ken                 | Yamada
-- Alejandro           | Rodriguez
-- Aleksandr           | Efimkin
-- Jae-Jin             | Park
-- Pablo               | Bernal
-- Hao                 | Li
-- Neil                | Murray
-- Anatoly             | Shevchenko
-- Celso               | Oliveira
-- Chen                | Li
-- Abdul Jamil         | Qureshi
-- Hans                | Johansson
-- Miguel              | Gonzalez
-- Cam                 | Loan
-- Wei                 | Wei
-- Adrian              | Bravo
-- Oleg                | Bazayev
SELECT person_first_name, person_last_name
FROM person INNER JOIN
     (SELECT pkp_symmetric.*
      FROM pkp_symmetric INNER JOIN
           (SELECT pkp_symmetric.* FROM pkp_symmetric INNER JOIN (SELECT person_id
                                                                  FROM person
                                                                  WHERE person_first_name='Hans'
                                                                    AND person_last_name='Johansson') AS TABELLE
                                                                 ON person_id=knows_person_id) AS TABELLE1
           ON pkp_symmetric.knows_person_id=TABELLE1.knows_other_person_id) AS TABELLE2
     ON person_id=knows_other_person_id

EXCEPT

SELECT p2.person_first_name, p2.person_last_name AS name
FROM pkp_symmetric
         JOIN person p1 ON pkp_symmetric.knows_person_id = p1.person_id
         JOIN person p2 ON pkp_symmetric.knows_other_person_id = p2.person_id
WHERE concat(p1.person_first_name, ' ', p1.person_last_name) = 'Hans Johansson';

--7. Welche Nutzer sind Mitglied in allen Foren, in denen auch ‘Mehmet Koksal’ Mitglied ist (Angabe Name)?
-------------
-- Records: 4
-------------
-- Chen Yang
-- Mehmet Koksal
-- Miguel Gonzalez
-- Paul Becker
-------------
WITH same_forums AS (
    SELECT hm.has_member_forum_id as forum_id
    FROM has_member hm
         JOIN person p ON hm.has_member_person_id = p.person_id
    WHERE concat(p.person_first_name, ' ', p.person_last_name) = 'Mehmet Koksal'
)
SELECT concat(p.person_first_name, ' ', p.person_last_name) AS name
FROM person p
    JOIN has_member hm ON p.person_id = hm.has_member_person_id
    JOIN same_forums sf ON hm.has_member_forum_id = sf.forum_id
GROUP BY name
HAVING count(hm.has_member_forum_id) = (SELECT count(sf.forum_id) FROM same_forums sf)
ORDER BY name;

--8. Geben Sie die prozentuale Verteilung der Nutzer bzgl. ihrer Herkunft aus verschiedenen Kontinenten an!
-------------
-- Records: 5
-------------
-- North_America |       9
-- South_America |       4
-- Africa        |      11
-- Asia          |      50
-- Europe        |      25
SELECT pl.place_name, (count(pl.place_name)*100/(SELECT count(*) FROM person AS p
                                                                    JOIN city AS ci ON ci.city_id=p.person_city_id
                                                                    JOIN country AS cou ON cou.country_id=ci.city_country_id
                                                                    JOIN place AS pl ON pl.place_id=cou.country_continent_id)) AS percent
FROM person AS p
    JOIN city AS ci ON ci.city_id=p.person_city_id
    JOIN country AS cou ON cou.country_id=ci.city_country_id
    JOIN place AS pl ON pl.place_id=cou.country_continent_id
GROUP BY pl.place_name;

--9. Zu welchen Themen (‘tag classes’) gibt es die meisten Posts? Geben Sie die Namen der Top 10 ‘tag classes’ mit ihrer Häufigkeit aus!
-------------
-- Records: 10
-------------
 -- Person         |   110
 -- MusicalArtist  |    99
 -- OfficeHolder   |    76
 -- Writer         |    66
 -- TennisPlayer   |    63
 -- BritishRoyalty |    57
 -- Saint          |    33
 -- Single         |    30
 -- Philosopher    |    28
 -- Album          |    27
SELECT tc.tag_class_name AS name, count(tc.tag_class_id) AS count
FROM post p
    JOIN message m ON p.post_message_id = m.message_id
    JOIN message_has_tag mht ON m.message_id = mht.message_has_tag_message_id
    JOIN has_type ht ON mht.message_has_tag_tag_id = ht.tag_id
    JOIN tag_class tc ON ht.tag_class_id = tc.tag_class_id
GROUP BY name
ORDER BY count DESC
limit 10;

--10. Welche Personen haben noch nie ein “Like” für einen Kommentar oder Post bekommen? Sortieren Sie die Ausgabe alphabetisch nach dem Nachnamen.
-------------
-- Records: 37
-------------
--Anmerkung: Aufgabenstellung impliziert auch Personen, die noch nie einen Post oder Kommentar verfasst haben.
SELECT *
FROM
((SELECT person_first_name, person_last_name FROM person)
EXCEPT
(SELECT p.person_first_name, p.person_last_name
        FROM person AS p
            JOIN message AS m ON p.person_id=m.message_person_id
            JOIN likes AS l ON l.likes_message_id=m.message_id)) AS x
ORDER BY x.person_last_name ASC;

--11. Welche Foren enthalten mehr Posts als die durchschnittliche Anzahl von Posts in Foren (Ausgabe alphabetisch sortiert nach Forumtitel)?
-------------
-- Records: 329
-------------
-- Album 0 of Abdul Haris Tobing,17
-- Album 0 of Alejandro Rodriguez,20
-- Album 0 of Ali Abouba,13
-- Album 0 of Amy Chen,19
-- Album 0 of Celso Oliveira,20
-- Album 0 of Djelaludin Zaland,15
-- Album 0 of Eric Mettacara,13
-- Album 0 of Fritz Engel,13
-- Album 0 of Hao Li,16
-- Album 0 of Jie Li,11
-- Album 0 of John Johnson,14
-- Album 0 of Jun Hu,16
-- Album 0 of Jun Li,18
-- Album 0 of Oleg Bazayev,17
-- Album 0 of Otto Redl,17
-- Album 0 of Wei Hu,15
-- Album 10 of Abdul Haris Tobing,12
-- Album 10 of Aleksandr Dobrunov,14
-- Album 10 of Alexei Codreanu,11
-- Album 10 of Ali Abouba,13
-- Album 10 of Alim Guliyev,11
-- Album 10 of Chen Yang,19
-- Album 10 of Eric Mettacara,16
-- Album 10 of Hao Li,19
-- Album 10 of Jun Hu,17
-- Album 10 of Oleg Bazayev,11
-- Album 10 of Tamas Gabor,14
-- Album 11 of Abdul Haris Tobing,17
-- Album 11 of Alec Lin,16
-- Album 11 of Aleksandr Dobrunov,20
-- Album 11 of Alexei Feltsman,15
-- Album 11 of Ali Abouba,20
-- Album 11 of Chen Li,12
-- Album 11 of Djelaludin Zaland,15
-- Album 11 of Fritz Engel,13
-- Album 11 of Hao Li,15
-- Album 11 of Jie Li,17
-- Album 11 of John Johnson,19
-- Album 11 of Jun Hu,11
-- Album 11 of Mirza Kalich Ali,16
-- ...
-------------
WITH forums_with_posts AS (
    SELECT f.forum_title AS name, count(p.post_id) AS count
    FROM forum f
        JOIN post p ON f.forum_id = p.post_forum_id
    GROUP BY f.forum_title
)
SELECT fwp.name, fwp.count
FROM forums_with_posts fwp
WHERE fwp.count > (
        SELECT avg(fwp2.count) AS average FROM forums_with_posts fwp2
    )
ORDER BY fwp.name;

--12. Welche Personen sind mit der Person befreundet, die die meisten Likes auf einen Post bekommen hat? Sortieren Sie die Ausgabe alphabetisch nach dem Nachnamen.
-------------
-- Records: 8
-------------
-- Neil              | Murray
-- Miguel            | Gonzalez
-- Roberto           | Diaz
-- Jorge             | Araujo Castro
-- Karl              | Fischer
-- Alejandro         | Rodriguez
-- Cam               | Loan
-- Chen              | Yang
SELECT person_first_name, person_last_name FROM (pkp_symmetric INNER JOIN
    (SELECT person_id, COUNT(*) as likes FROM
        LIKES INNER JOIN (PERSON INNER JOIN MESSAGE ON message_person_id=person_id) ON likes_message_id=message_id
     GROUP BY person_id
     ORDER BY likes DESC
     LIMIT 1) AS TABELLE
    ON knows_person_id=person_id) INNER JOIN person ON knows_other_person_id=person.person_id;

--13. Welche Personen sind direkt oder indirekt mit ‘Jun Hu’ (id 94) verbunden (befreundet)? Geben Sie für jede Person die Distanz zu Jun an.
--TODO:
-------------
-- Records: ???
-------------
WITH RECURSIVE RelationshipCte (person_id, friend_id, depth, is_in_p, is_in_f) AS
   (
       SELECT
           p.person_id AS person_id,
           p.person_id AS friend_id,
           0 as depth,
           false, false
       FROM person p
       WHERE p.person_id = 94

       UNION

       SELECT DISTINCT ON (friend_id)
           k.knows_person_id AS person_id,
           k.knows_other_person_id AS friend_id,
           depth + 1,
           k.knows_other_person_id NOT IN (cte.person_id),
           k.knows_other_person_id NOT IN (cte.friend_id)
       FROM RelationshipCte cte
                JOIN pkp_symmetric k ON cte.friend_id = k.knows_person_id
            --WHERE NOT k.person2 = 94 AND depth < 5 AND k.person2 IN RelationshipCte(person_id)
       WHERE k.knows_other_person_id NOT IN (cte.friend_id) AND depth < 5
       --WHERE k.person2 NOT IN (SELECT person_id FROM RelationshipCte)
   )
SELECT
    r.person_id,
    concat(p.person_first_name, ' ', p.person_last_name) AS person_name,
    r.friend_id,
    concat(f.person_first_name, ' ', f.person_last_name) AS friend_name,
    depth,
    r.is_in_p, r.is_in_f
FROM RelationshipCte r
         JOIN person p ON r.person_id = p.person_id
         JOIN person f ON r.friend_id = f.person_id
--GROUP BY friend_name, depth
ORDER BY depth, friend_name;

--2. Variante:
WITH RECURSIVE knows_junhu(knows_direct, knows_indirect, distance, path, cycle) AS (
    SELECT pkp.knows_person_id, pkp.knows_other_person_id, 0, array[pkp.knows_person_id], false
    FROM pkp_symmetric pkp
    WHERE pkp.knows_person_id = 94

    union

    select pkp.knows_person_id, pkp.knows_other_person_id, kj.distance + 1, path ||  pkp.knows_person_id, pkp.knows_person_id = ANY(path)
    FROM knows_junhu kj inner join pkp_symmetric pkp on pkp.knows_person_id = kj.knows_indirect
    WHERE pkp.knows_other_person_id <> 94 and not cycle and pkp.knows_person_id = kj.knows_indirect
)
select * from knows_junhu;

--14. Erweitern Sie die Anfrage zu Aufgabe 13 indem Sie zusätzlich zur Distanz den Pfad zwischen den Nutzern ausgeben.
--TODO: