--liquibase formatted sql
--changeset galkinki:alter_users_table_add_tree_columns
ALTER TABLE users ADD COLUMN first_arg TEXT;
ALTER TABLE users ADD COLUMN second_arg TEXT;
ALTER TABLE users ADD COLUMN result TEXT;
