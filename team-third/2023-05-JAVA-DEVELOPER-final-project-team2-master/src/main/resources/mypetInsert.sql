insert into my_pet (mypet_no,mypet_name,mypet_kind,mypet_birthday,user_id) 
VALUES (mypet_mypet_no_seq.nextval,'보리','강아지',TO_DATE('2022/04/05','YYYY/MM/DD'),'박태환');

insert into my_pet (mypet_no,mypet_name,mypet_kind,mypet_birthday,user_id) 
VALUES (mypet_mypet_no_seq.nextval,'율무','강아지',TO_DATE('2022/11/11','YYYY/MM/DD'),'전아현');

insert into my_pet (mypet_no,mypet_name,mypet_kind,mypet_birthday,user_id) 
VALUES (mypet_mypet_no_seq.nextval,'나비','고양이',TO_DATE('2022/07/05','YYYY/MM/DD'),'김창섭');

commit;