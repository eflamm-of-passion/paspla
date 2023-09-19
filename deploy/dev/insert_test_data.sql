insert into workflows (description,name) values ('doing stuff','some workflow');

insert into http_request_actions (body,headers,http_verb,workflow_id,query_params,rank,url) 
values ('','','GET',(select id from workflows limit 1),'',1,'https://pokeapi.co/api/v2/pokemon/ditto');

insert into send_mail_actions (attachment_filename,body,carbon_copy_recipients,invisible_carbon_copy_recipients,workflow_id,rank,recipients,sender) 
values ('','hello world','someotherclient@mail.com','',(select id from workflows limit 1),2,'client@mail.com','admin@mail.com');
