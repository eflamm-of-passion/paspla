CREATE SCHEMA IF NOT EXISTS paspla;

CREATE TABLE IF NOT EXISTS jobs (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    name VARCHAR (255) NOT NULL,
    description VARCHAR (255) NOT NULL
);

CREATE INDEX idx_rules_uuid ON rules(uuid);

CREATE TABLE IF NOT EXISTS actions (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    rule_id INT REFERENCES rules (id) ON DELETE CASCADE,
    rank INT NOT NULL,
    type VARCHAR (255) NOT NULL,
    value VARCHAR (255) NOT NULL
);

CREATE INDEX idx_actions_uuid ON actions(uuid);