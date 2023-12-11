
/**********************orders select************************/
--1. 멤버 한사람의(kimshuttle11) 주문전체목록
select o_no, o_date, o_price, o_desc, member_Id from orders where member_id = 'kimshuttle11';

--2. 주문한개(멤버 한사람의) 
select * from orders where o_no = 6;

--3. 주문한개의 주문상세 여러개(주문상세)
select * from order_item where o_no = 7;

--4. 주문한개의 주문상세,제품정보 여러개(주문상세,제품)
select * from orders o join order_item oi on o.o_no = oi.o_no  join  product p on oi.p_no = p.p_no 
where o.member_id = 'kimshuttle11' and o.o_no = 1;

desc product;

/**********************orders delete************************/
--1. 주문한개삭제(주문1개삭제,주문상세삭제)
delete from order_item where o_no = 7;
delete from orders where o_no = 6;

--2. 멤버 한사람의(leeshuttle22) 주문내역 전체삭제
delete from order_item where o_no in(select o_no from orders where member_id='kimshuttle11');
delete from orders where member_id = 'kimshuttle11';


