FROM mysql:8.1.0

ENV MYSQL_ROOT_PASSWORD frost

COPY create_tables.sql /docker-entrypoint-initdb.d/

EXPOSE 3306