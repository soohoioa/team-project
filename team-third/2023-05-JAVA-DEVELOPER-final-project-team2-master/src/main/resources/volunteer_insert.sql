INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 09, sysdate, '봉사완료', 1, 1);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 11, sysdate, '봉사완료', 2, 7);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 13, sysdate, '봉사완료', 4, 8);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 10, sysdate, '봉사완료', 2, 2);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 11, sysdate, '봉사완료', 3, 3);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 09, sysdate, '봉사완료', 1, 9);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 10, sysdate, '봉사완료', 2, 10);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 11, sysdate, '봉사완료', 4, 4);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 12, sysdate, '봉사완료', 6, 5);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 12, sysdate, '봉사완료', 1, 6);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 11, sysdate, '봉사완료', 2, 7);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 13, sysdate, '봉사완료', 4, 8);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 09, sysdate, '봉사완료', 1, 9);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 10, sysdate, '봉사완료', 2, 10);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 11, sysdate, '봉사완료', 3, 11);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 11, sysdate, '봉사완료', 4, 12);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 12, sysdate, '봉사완료', 6, 13);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 15, sysdate, '봉사신청중', 1, 14);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 15, sysdate, '봉사신청중', 2, 15);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 16, sysdate, '봉사신청중', 4, 16);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 17, sysdate, '봉사신청중', 6, 17);
INSERT INTO volunteer (volunteer_no, volunteer_time, volunteer_date, volunteer_status, user_no, center_no) 
VALUES(Volunteer_volunteer_no_SEQ.nextval, 18, sysdate, '봉사신청중', 9, 18);




INSERT INTO center (center_no, center_name, center_phone_number, center_local, center_open_close_time) VALUES ('11','안녕보호소','010-1111-1111','서울시','09:00 ~ 21:00');
INSERT INTO center (center_no, center_name, center_phone_number, center_local, center_open_close_time) VALUES ('22','사랑보호소','010-2222-2222','경기도','09:00 ~ 22:00');
INSERT INTO center (center_no, center_name, center_phone_number, center_local, center_open_close_time) VALUES ('33','하하보호소','010-3333-3333','강원도','09:00 ~ 23:00');
INSERT INTO center (center_no, center_name, center_phone_number, center_local, center_open_close_time) VALUES ('44','소소보호소','010-4444-4444','충청도','09:00 ~ 24:00');


select * from review_board b join userinfo u on b.user_id = u.user_id ORDER by board_star desc;


INSERT INTO review_board (review_board.board_no, board_date, board_title, board_content, board_star, user_no) 
VALUES (1, sysdate, '타이틀1', '내용1', 5, 2);
INSERT INTO review_board (review_board.board_no, board_date, board_title, board_content, board_star, user_no) 
VALUES (2, sysdate, '타이틀2', '내용2', 4, 5);
INSERT INTO review_board (review_board.board_no, board_date, board_title, board_content, board_star, user_no) 
VALUES (3, sysdate, '타이틀3', '내용3', 3, 1);
INSERT INTO review_board (review_board.board_no, board_date, board_title, board_content, board_star, user_no)
VALUES (4, sysdate, '타이틀4', '내용4', 1, 2);

