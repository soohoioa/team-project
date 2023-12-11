/**********************orders insert************************/

insert into orders(o_no,o_date,o_price,o_desc,member_id) VALUES (orders_o_no_SEQ.nextval, sysdate-1, 44000, '촉촉한쵹호칩 외 2종', 'kimshuttle11');
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 1); 
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 3);
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 4);

insert into orders(o_no,o_date,o_price,o_desc,member_id) VALUES (orders_o_no_SEQ.nextval, sysdate, 112000, '파리도좋아하는 바게뜨 외1종', 'leeshuttle22');
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 2);
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 8);

insert into orders(o_no,o_date,o_price,o_desc,member_id) VALUES (orders_o_no_SEQ.nextval, sysdate-2, 5000, '따뜻한 아이스 아메리카노 외0종', 'parkshuttle33');
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 9);

insert into orders(o_no,o_date,o_price,o_desc,member_id) VALUES (orders_o_no_SEQ.nextval, sysdate, 37000, '이퇄~리안 피자빵 외 2종', 'choishuttle44');
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 5);
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 6);
insert into order_item(oi_no, oi_qty, o_no, p_no) VALUES(order_item_oi_no_SEQ.nextval, 1, orders_o_no_SEQ.currval, 7);

insert into orders(o_no,o_date,o_price,o_desc,member_id) VALUES (orders_o_no_SEQ.nextval, sysdate, 7000, '카라멜레온마끼아토', 'hanshuttle155');

select * from orders;

commit;