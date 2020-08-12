#!/bin/bash

# Start the script to create the DB and user
bash /testdb/configure-mssql-db.sh &

# Start SQL Server
exec /opt/mssql/bin/sqlservr
