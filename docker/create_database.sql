CREATE SCHEMA IF NOT EXISTS paspla;

CREATE TABLE IF NOT EXISTS jobs (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    name VARCHAR (255) NOT NULL,
    description VARCHAR (255) NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_jobs_uuid ON jobs(uuid);

CREATE TABLE IF NOT EXISTS send_mail_actions (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    job_id INT REFERENCES jobs (id) ON DELETE CASCADE,
    rank INT NOT NULL,
    sender VARCHAR (255),
    recipients VARCHAR (255) NOT NULL,
    carbon_copy_recipients VARCHAR (255),
    invisible_carbon_copy_recipients VARCHAR (255),
    attachment_filename VARCHAR (255)
);
CREATE INDEX IF NOT EXISTS idx_send_mail_actions_uuid ON send_mail_actions(uuid);

CREATE TABLE IF NOT EXISTS http_request_actions (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    job_id INT REFERENCES jobs (id) ON DELETE CASCADE,
    rank INT NOT NULL,
    url VARCHAR (255),
    http_verb VARCHAR (255),
    query_params VARCHAR (255),
    headers VARCHAR (255),
    body VARCHAR (1023)
);
CREATE INDEX IF NOT EXISTS idx_http_request_actions_uuid ON http_request_actions(uuid);