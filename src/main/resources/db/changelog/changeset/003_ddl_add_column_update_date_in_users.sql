--liquibase formatted sql
--changeset galkinki:alter_users_table_add_column_update_date

ALTER TABLE users ADD COLUMN update_date TIMESTAMP WITHOUT TIME ZONE;

--rollback ALTER TABLE users DROP COLUMN update_date;