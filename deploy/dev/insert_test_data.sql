-- workflow
insert into workflows (description,name, is_enabled) values ('doing stuff','some workflow', false);

-- http request
insert into http_request_actions (body,http_verb,workflow_id,query_params,rank,url) 
values ('','GET',(select id from workflows order by id desc limit 1),'',1,'https://pokeapi.co/api/v2/pokemon/ditto');
insert into http_request_actions (http_request_action_id, header_key,header_value) 
values ((select id from http_request_actions order by id desc limit 1), 'Cookie', 'foo=hello; bar=world');

-- send mail
insert into send_mail_actions (attachment_filename,body,carbon_copy_recipients,invisible_carbon_copy_recipients,workflow_id,rank,recipients,sender) 
values ('','hello world','someotherclient@mail.com','',(select id from workflows order by id desc limit 1),2,'client@mail.com','admin@mail.com');
