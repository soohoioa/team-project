/**********cart insert**********/
/********cart select *********/

--leeshuttle22 멤버한사람의 카트아이템리스트
select * from cart c 
join userinfo u on c.member_id = u.member_id 
join product p on p.p_no = c.p_no where u.member_id = 'leeshuttle22';
       
-- 회원의 전체 주문목록 
select * from cart c join product p on c.p_no=p.p_no where c.member_id ='kimshuttle11';

-- 회원의 주문 한개
select * from cart c join product p on c.p_no=p.p_no where c.member_id ='kimshuttle11' and p.p_no = 1;

-- 회원 한개주문 업데이트
update cart set cart_qty = cart_qty+10 where member_id ='kimshuttle11'  and p_no = 1 ;
-- 회원 한개주문 업데이트 2
update cart set cart_qty =15 where member_id ='leeshuttle22'  and p_no = 1 ;
-- 회원의 전체주문 업데이트
update cart set cart_qty =7 where member_id ='leeshuttle22' ;

-- 회원 주문삭제
delete from cart where member_id='kimshuttle11';

--�회원 삭제할 품목 선택삭제
delete from cart where member_id='kimshuttle11' and p_no=1;

commit;