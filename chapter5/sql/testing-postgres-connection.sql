select * 
from licenses ls 
inner join organizations os 
on ls.organization_id = os.organization_id
-- WHERE os.organization_id = 'd898a142-de44-466c-8c88-9ceb2c2429d3'
order by ls.organization_id, ls.license_id ;

-- comment is a keyword thereofore changing it is tricky
update licenses set "comment" = 'No comments added on Monday 10-05-2021' where license_id = 'f2a9c9d4-d2c0-44fa-97fe-724d77173c62';
update licenses set "comment" = 'Not interested added on Monday 10-05-2021.' where license_id = '279709ff-e6d5-4a54-8b55-a5c37542025b';
update licenses set description = 'Hugely Improved Software Product' where license_id = 'f2a9c9d4-d2c0-44fa-97fe-724d77173c62';
update licenses set description = 'Highly Overrated Software Product' where license_id = '279709ff-e6d5-4a54-8b55-a5c37542025b';
commit;

insert into licenses (license_id, organization_id, description, product_name, license_type, "comment") 
values ('whatever-licence-id', 'd898a142-de44-466c-8c88-9ceb2c2429d3', 'inserted licence', 'VEA-STOCK', 'rubbish', 'Will I be seen?');
insert into licenses (license_id, organization_id, description, product_name, license_type, "comment") 
values ('whatever-licence-id-2', 'd898a142-de44-466c-8c88-9ceb2c2429d3', '2nd inserted licence', 'VEA-STOCK upgrade', 'still rubbish', 'Added on Monday 10-05-2021');