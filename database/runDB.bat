"C:\Program Files\PostgreSQL\12\bin\psql.exe" -U postgres -f createDB.sql postgres
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -U postgres -f createTables.sql socialnetwork
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -U postgres -f pkp_symmetric.sql socialnetwork
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -U postgres -f createTriggers.sql socialnetwork
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -U postgres -f shortestFriendshipPath.sql socialnetwork