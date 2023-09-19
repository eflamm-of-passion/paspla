CREATE SCHEMA IF NOT EXISTS paspla;

CREATE TABLE IF NOT EXISTS workflows (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    name VARCHAR (255) NOT NULL,
    description VARCHAR (255) NOT NULL,
    is_enabled BOOLEAN DEFAULT TRUE
);
CREATE INDEX IF NOT EXISTS idx_workflows_uuid ON workflows(uuid);

CREATE TABLE IF NOT EXISTS send_mail_actions (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    workflow_id INT REFERENCES workflows (id) ON DELETE CASCADE,
    rank INT NOT NULL,
    sender VARCHAR (255),
    recipients VARCHAR (255) NOT NULL,
    carbon_copy_recipients VARCHAR (255),
    invisible_carbon_copy_recipients VARCHAR (255),
    attachment_filename VARCHAR (255),
    body VARCHAR (1023)
);
CREATE INDEX IF NOT EXISTS idx_send_mail_actions_uuid ON send_mail_actions(uuid);

CREATE TABLE IF NOT EXISTS http_request_actions (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    workflow_id INT REFERENCES workflows (id) ON DELETE CASCADE,
    rank INT NOT NULL,
    url VARCHAR (255),
    http_verb VARCHAR (255),
    query_params VARCHAR (255),
    body VARCHAR (1023)
);
CREATE INDEX IF NOT EXISTS idx_http_request_actions_uuid ON http_request_actions(uuid);

CREATE TABLE IF NOT EXISTS http_request_headers (
    id serial PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid(),
    http_request_action_id INT REFERENCES http_request_actions (id) ON DELETE CASCADE,
    header_key VARCHAR (255),
    header_value VARCHAR (255)
);
CREATE INDEX IF NOT EXISTS idx_http_request_headers_uuid ON http_request_headers(uuid);
