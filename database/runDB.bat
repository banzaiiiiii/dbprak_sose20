"D:\Program Files\PostgreSQL\bin\psql.exe" -U postgres -f createDB.sql postgres
"D:\Program Files\PostgreSQL\bin\psql.exe" -U postgres -f createTables.sql socialnetwork
"D:\Program Files\PostgreSQL\bin\psql.exe" -U postgres -f pkp_symmetric.sql socialnetwork
