#!/bin/sh
psql -U postgres -f createDB.sql postgres
psql -U postgres -f createTables.sql socialnetwork