--liquibase formatted sql
--changeset galkinki:create_calc_events_table
CREATE TABLE calc_events (
                       id SERIAL PRIMARY KEY,
                       user_id INT NOT NULL REFERENCES users(id),
                       first INT NOT NULL,
                       second INT NOT NULL,
                       result INT NOT NULL,
                       create_date TIMESTAMP default now(),
                       type TEXT NOT NULL
);

--rollback DROP TABLE calc_events;