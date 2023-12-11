insert into orderstatus(os_no,os_image,os_desc) 
VALUES (ORDER_STATUS_NO_SEQ.nextval,'주문확인.jpg','주문확인');

insert into orderstatus(os_no,os_image,os_desc) 
VALUES (ORDER_STATUS_NO_SEQ.nextval,'배송준비.jpg','배송준비');

insert into orderstatus(os_no,os_image,os_desc) 
VALUES (ORDER_STATUS_NO_SEQ.nextval,'배송중.jpg','배송중');

insert into orderstatus(os_no,os_image,os_desc) 
VALUES (ORDER_STATUS_NO_SEQ.nextval,'배송완료.jpg','배송완료');

insert into pet(pet_no,pet_local,pet_character,pet_find_place,pet_register_date,pet_type,pet_gender,center_no,pet_image) 
VALUES (pet_pet_no_seq.nextval,'서울특별시','온순하다','서울특별시',sysdate,'강아지','남',1,'ddd');
;