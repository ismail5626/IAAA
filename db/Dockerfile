# Use the MySQL image version 8.3 (allows native password plugin)
FROM mysql:8.3
# Copy the main SQL file to docker-entrypoint-initdb.d.
# Scripts and SQL files in this folder are executed on container startup.
# This is specific to MySQL.
ENV MYSQL_DATABASE=test
COPY ./test.sql /docker-entrypoint-initdb.d
# Set the root password
ENV MYSQL_ROOT_PASSWORD root