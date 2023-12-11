-- delete
delete from userinfo where member_id='kimshuttle11';

-- update
update userinfo set 
    member_password='1234',
     member_email='xxxx@naver.com',
    member_address='LA',
    member_number='010-xxxx-xxxx'
where member_id='kimshuttle11';


-- selete pk
select member_id, 
       member_password, 
       member_name, 
       member_email, 
       member_address, 
       member_birth, 
       member_number, 
       member_regdate, 
       member_point
from userinfo where member_id = 'parkshuttle33';

-- select all
select member_id, 
       member_password, 
       member_name, 
       member_email, 
       member_address, 
       member_birth, 
       member_number, 
       member_regdate, 
       member_point
from userinfo;

commit;

desc userinfo;