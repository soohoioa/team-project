/**********************************/
/* Table Name: UserInfo */
/**********************************/
CREATE TABLE UserInfo(
		user_no                       		NUMBER(10)		 NULL ,
		user_password                 		VARCHAR2(50)		 NULL ,
		user_point                    		NUMBER(10)		 NULL ,
		user_gender                   		VARCHAR2(50)		 NULL ,
		user_address                  		VARCHAR2(50)		 NULL ,
		user_phone_number             		VARCHAR2(50)		 NULL ,
		user_id                       		VARCHAR2(50)		 NULL ,
		user_resident_number          		VARCHAR2(50)		 NULL ,
		user_register_date            		TIMESTAMP(9)		 NULL ,
		user_name                     		VARCHAR2(50)		 NULL ,
		user_coupon_year              		NUMBER(10)		 NULL 
);

CREATE SEQUENCE UserInfo_user_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER UserInfo_user_no_TRG
BEFORE INSERT ON UserInfo
FOR EACH ROW
BEGIN
IF :NEW.user_no IS NOT NULL THEN
  SELECT UserInfo_user_no_SEQ.NEXTVAL INTO :NEW.user_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE UserInfo is 'UserInfo';
COMMENT ON COLUMN UserInfo.user_no is 'user_no';
COMMENT ON COLUMN UserInfo.user_password is 'user_password';
COMMENT ON COLUMN UserInfo.user_point is 'user_point';
COMMENT ON COLUMN UserInfo.user_gender is 'user_gender';
COMMENT ON COLUMN UserInfo.user_address is 'user_address';
COMMENT ON COLUMN UserInfo.user_phone_number is 'user_phone_number';
COMMENT ON COLUMN UserInfo.user_id is 'user_id';
COMMENT ON COLUMN UserInfo.user_resident_number is 'user_resident_number';
COMMENT ON COLUMN UserInfo.user_register_date is 'user_register_date';
COMMENT ON COLUMN UserInfo.user_name is 'user_name';
COMMENT ON COLUMN UserInfo.user_coupon_year is 'user_coupon_year';


/**********************************/
/* Table Name: Coupon */
/**********************************/
CREATE TABLE Coupon(
		coupon_id                     		NUMBER(10)		 NULL ,
		coupon_name                   		VARCHAR2(50)		 NULL ,
		coupon_discount               		NUMBER(10)		 NULL ,
		coupon_expiration_date        		TIMESTAMP(10)		 NULL ,
		coupon_payday                 		TIMESTAMP(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Coupon_coupon_id_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Coupon_coupon_id_TRG
BEFORE INSERT ON Coupon
FOR EACH ROW
BEGIN
IF :NEW.coupon_id IS NOT NULL THEN
  SELECT Coupon_coupon_id_SEQ.NEXTVAL INTO :NEW.coupon_id FROM DUAL;
END IF;
END;

COMMENT ON TABLE Coupon is 'Coupon';
COMMENT ON COLUMN Coupon.coupon_id is 'coupon_id';
COMMENT ON COLUMN Coupon.coupon_name is 'coupon_name';
COMMENT ON COLUMN Coupon.coupon_discount is 'coupon_discount';
COMMENT ON COLUMN Coupon.coupon_expiration_date is 'coupon_expiration_date';
COMMENT ON COLUMN Coupon.coupon_payday is 'coupon_payday';
COMMENT ON COLUMN Coupon.user_no is 'user_no';


/**********************************/
/* Table Name: ReportBoard */
/**********************************/
CREATE TABLE ReportBoard(
		board_no                      		NUMBER(10)		 NULL ,
		board_title                   		VARCHAR2(100)		 NULL ,
		board_register_date           		DATE		 NULL ,
		board_content                 		VARCHAR2(100)		 NULL ,
		board_find_date               		DATE		 NULL ,
		board_readCount               		NUMBER(10)		 NULL ,
		board_find_name               		VARCHAR2(50)		 NULL ,
		board_find_phone              		VARCHAR2(50)		 NULL ,
		user_no                       		NUMBER(10)		 NULL ,
		board_image                   		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE ReportBoard_board_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER ReportBoard_board_no_TRG
BEFORE INSERT ON ReportBoard
FOR EACH ROW
BEGIN
IF :NEW.board_no IS NOT NULL THEN
  SELECT ReportBoard_board_no_SEQ.NEXTVAL INTO :NEW.board_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE ReportBoard is 'ReportBoard';
COMMENT ON COLUMN ReportBoard.board_no is 'board_no';
COMMENT ON COLUMN ReportBoard.board_title is 'board_title';
COMMENT ON COLUMN ReportBoard.board_register_date is 'board_register_date';
COMMENT ON COLUMN ReportBoard.board_content is 'board_content';
COMMENT ON COLUMN ReportBoard.board_find_date is 'board_find_date';
COMMENT ON COLUMN ReportBoard.board_readCount is 'board_readCount';
COMMENT ON COLUMN ReportBoard.board_find_name is 'board_find_name';
COMMENT ON COLUMN ReportBoard.board_find_phone is 'board_find_phone';
COMMENT ON COLUMN ReportBoard.user_no is 'user_no';
COMMENT ON COLUMN ReportBoard.board_image is 'board_image';


/**********************************/
/* Table Name: Local */
/**********************************/
CREATE TABLE Local(
		local_no                      		NUMBER(10)		 NULL ,
		local_city                    		VARCHAR2(50)		 NULL ,
		local_gu                      		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE Local_local_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Local_local_no_TRG
BEFORE INSERT ON Local
FOR EACH ROW
BEGIN
IF :NEW.local_no IS NOT NULL THEN
  SELECT Local_local_no_SEQ.NEXTVAL INTO :NEW.local_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Local is 'Local';
COMMENT ON COLUMN Local.local_no is 'local_no';
COMMENT ON COLUMN Local.local_city is 'local_city';
COMMENT ON COLUMN Local.local_gu is 'local_gu';


/**********************************/
/* Table Name: Center */
/**********************************/
CREATE TABLE Center(
		center_no                     		NUMBER(10)		 NULL ,
		center_name                   		VARCHAR2(50)		 NULL ,
		center_phone_number           		VARCHAR2(50)		 NULL ,
		center_local                  		VARCHAR2(50)		 NULL ,
		center_open_close_time        		VARCHAR2(50)		 NULL ,
		local_no                      		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Center_center_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Center_center_no_TRG
BEFORE INSERT ON Center
FOR EACH ROW
BEGIN
IF :NEW.center_no IS NOT NULL THEN
  SELECT Center_center_no_SEQ.NEXTVAL INTO :NEW.center_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Center is 'Center';
COMMENT ON COLUMN Center.center_no is 'center_no';
COMMENT ON COLUMN Center.center_name is 'center_name';
COMMENT ON COLUMN Center.center_phone_number is 'center_phone';
COMMENT ON COLUMN Center.center_local is 'center_local';
COMMENT ON COLUMN Center.center_open_close_time is 'center_open_close_time';
COMMENT ON COLUMN Center.local_no is 'local_no';


/**********************************/
/* Table Name: Volunteer */
/**********************************/
CREATE TABLE Volunteer(
		volunteer_no                  		NUMBER(10)		 NULL ,
		volunteer_time                		NUMBER(10)		 NULL ,
		volunteer_date                		DATE		 NULL ,
		center_no                     		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL ,
		volunteer_status              		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE Volunteer_volunteer_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Volunteer_volunteer_no_TRG
BEFORE INSERT ON Volunteer
FOR EACH ROW
BEGIN
IF :NEW.volunteer_no IS NOT NULL THEN
  SELECT Volunteer_volunteer_no_SEQ.NEXTVAL INTO :NEW.volunteer_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Volunteer is 'Volunteer';
COMMENT ON COLUMN Volunteer.volunteer_no is 'volunteer_no';
COMMENT ON COLUMN Volunteer.volunteer_time is 'volunteer_time';
COMMENT ON COLUMN Volunteer.volunteer_date is 'volunteer_date';
COMMENT ON COLUMN Volunteer.center_no is 'center_no';
COMMENT ON COLUMN Volunteer.user_no is 'user_no';
COMMENT ON COLUMN Volunteer.volunteer_status is 'volunteer_status';


/**********************************/
/* Table Name: Visit */
/**********************************/
CREATE TABLE Visit(
		visit_no                      		NUMBER(10)		 NULL ,
		visit_time                    		NUMBER(10)		 NULL ,
		visit_date                    		DATE		 NULL ,
		center_no                     		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL ,
		visit_status                  		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE Visit_visit_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Visit_visit_no_TRG
BEFORE INSERT ON Visit
FOR EACH ROW
BEGIN
IF :NEW.visit_no IS NOT NULL THEN
  SELECT Visit_visit_no_SEQ.NEXTVAL INTO :NEW.visit_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Visit is 'Visit';
COMMENT ON COLUMN Visit.visit_no is 'visit_no';
COMMENT ON COLUMN Visit.visit_time is 'visit_time';
COMMENT ON COLUMN Visit.visit_date is 'visit_date';
COMMENT ON COLUMN Visit.center_no is 'center_no';
COMMENT ON COLUMN Visit.user_no is 'user_no';
COMMENT ON COLUMN Visit.visit_status is 'visit_status';


/**********************************/
/* Table Name: Pet */
/**********************************/
CREATE TABLE Pet(
		pet_local                     		VARCHAR2(50)		 NULL ,
		pet_no                        		NUMBER(10)		 NULL ,
		pet_type                      		VARCHAR2(50)		 NULL ,
		pet_gender                    		VARCHAR2(50)		 NULL ,
		pet_register_date             		DATE		 NULL ,
		pet_find_place                		VARCHAR2(50)		 NULL ,
		pet_character                 		VARCHAR2(50)		 NULL ,
		center_no                     		NUMBER(10)		 NULL ,
		local_no                      		NUMBER(10)		 NULL ,
		pet_image                     		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE Pet_pet_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Pet_pet_no_TRG
BEFORE INSERT ON Pet
FOR EACH ROW
BEGIN
IF :NEW.pet_no IS NOT NULL THEN
  SELECT Pet_pet_no_SEQ.NEXTVAL INTO :NEW.pet_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Pet is 'Pet';
COMMENT ON COLUMN Pet.pet_local is 'pet_local';
COMMENT ON COLUMN Pet.pet_no is 'pet_no';
COMMENT ON COLUMN Pet.pet_type is 'pet_type';
COMMENT ON COLUMN Pet.pet_gender is 'pet_gender';
COMMENT ON COLUMN Pet.pet_register_date is 'pet_register_date';
COMMENT ON COLUMN Pet.pet_find_place is 'pet_find_place';
COMMENT ON COLUMN Pet.pet_character is 'pet_character';
COMMENT ON COLUMN Pet.center_no is 'center_no';
COMMENT ON COLUMN Pet.local_no is 'local_no';
COMMENT ON COLUMN Pet.pet_image is 'pet_image';


/**********************************/
/* Table Name: Adopt */
/**********************************/
CREATE TABLE Adopt(
		adopt_no                      		NUMBER(10)		 NULL ,
		adopt_time                    		NUMBER(10)		 NULL ,
		adopt_date                    		DATE		 NULL ,
		adopt_status                  		VARCHAR2(50)		 NULL ,
		pet_no                        		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Adopt_adopt_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Adopt_adopt_no_TRG
BEFORE INSERT ON Adopt
FOR EACH ROW
BEGIN
IF :NEW.adopt_no IS NOT NULL THEN
  SELECT Adopt_adopt_no_SEQ.NEXTVAL INTO :NEW.adopt_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Adopt is 'Adopt';
COMMENT ON COLUMN Adopt.adopt_no is 'adopt_no';
COMMENT ON COLUMN Adopt.adopt_time is 'adopt_time';
COMMENT ON COLUMN Adopt.adopt_date is 'adopt_date';
COMMENT ON COLUMN Adopt.adopt_status is 'adopt_status';
COMMENT ON COLUMN Adopt.pet_no is 'pet_no';
COMMENT ON COLUMN Adopt.user_no is 'user_no';


/**********************************/
/* Table Name: Product */
/**********************************/
CREATE TABLE Product(
		product_no                    		NUMBER(10)		 NULL ,
		product_name                  		VARCHAR2(50)		 NULL ,
		product_price                 		NUMBER(10)		 NULL ,
		product_category              		VARCHAR2(50)		 NULL ,
		product_pet_category          		VARCHAR2(10)		 NULL ,
		product_qty                   		NUMBER(10)		 NULL ,
		product_image                 		VARCHAR2(50)		 NULL ,
		product_detail_image          		VARCHAR2(1000)		 NULL ,
		product_star_avg              		DOUBLE PRECISION		 NULL 
);

CREATE SEQUENCE Product_product_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Product_product_no_TRG
BEFORE INSERT ON Product
FOR EACH ROW
BEGIN
IF :NEW.product_no IS NOT NULL THEN
  SELECT Product_product_no_SEQ.NEXTVAL INTO :NEW.product_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Product is 'Product';
COMMENT ON COLUMN Product.product_no is 'product_no';
COMMENT ON COLUMN Product.product_name is 'product_name';
COMMENT ON COLUMN Product.product_price is 'product_price';
COMMENT ON COLUMN Product.product_category is 'product_category';
COMMENT ON COLUMN Product.product_pet_category is 'product_pet_category';
COMMENT ON COLUMN Product.product_qty is 'product_qty';
COMMENT ON COLUMN Product.product_image is 'product_image';
COMMENT ON COLUMN Product.product_detail_image is 'product_detail_image';
COMMENT ON COLUMN Product.product_star_avg is 'product_star_avg';


/**********************************/
/* Table Name: Orders */
/**********************************/
CREATE TABLE Orders(
		order_no                      		NUMBER(10)		 NULL ,
		order_date                    		DATE		 NULL ,
		order_price                   		NUMBER(10)		 NULL ,
		order_address                 		VARCHAR2(100)		 NULL ,
		order_desc                    		VARCHAR2(100)		 NULL ,
		user_no                       		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Orders_order_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Orders_order_no_TRG
BEFORE INSERT ON Orders
FOR EACH ROW
BEGIN
IF :NEW.order_no IS NOT NULL THEN
  SELECT Orders_order_no_SEQ.NEXTVAL INTO :NEW.order_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Orders is 'Orders';
COMMENT ON COLUMN Orders.order_no is 'order_no';
COMMENT ON COLUMN Orders.order_date is 'order_date';
COMMENT ON COLUMN Orders.order_price is 'order_price';
COMMENT ON COLUMN Orders.order_address is 'order_address';
COMMENT ON COLUMN Orders.order_desc is 'order_desc';
COMMENT ON COLUMN Orders.user_no is 'user_no';


/**********************************/
/* Table Name: OrderStatus */
/**********************************/
CREATE TABLE OrderStatus(
		os_no                         		NUMBER(10)		 NULL ,
		os_image                      		VARCHAR2(50)		 NULL ,
		os_desc                       		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE OrderStatus_os_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER OrderStatus_os_no_TRG
BEFORE INSERT ON OrderStatus
FOR EACH ROW
BEGIN
IF :NEW.os_no IS NOT NULL THEN
  SELECT OrderStatus_os_no_SEQ.NEXTVAL INTO :NEW.os_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE OrderStatus is 'OrderStatus';
COMMENT ON COLUMN OrderStatus.os_no is 'os_no';
COMMENT ON COLUMN OrderStatus.os_image is 'os_image';
COMMENT ON COLUMN OrderStatus.os_desc is 'os_desc';


/**********************************/
/* Table Name: Order_item */
/**********************************/
CREATE TABLE Order_item(
		oi_no                         		NUMBER(10)		 NULL ,
		oi_qty                        		NUMBER(10)		 NULL ,
		order_no                      		NUMBER(10)		 NULL ,
		product_no                    		NUMBER(10)		 NULL ,
		os_no                         		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Order_item_oi_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Order_item_oi_no_TRG
BEFORE INSERT ON Order_item
FOR EACH ROW
BEGIN
IF :NEW.oi_no IS NOT NULL THEN
  SELECT Order_item_oi_no_SEQ.NEXTVAL INTO :NEW.oi_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Order_item is 'Order_item';
COMMENT ON COLUMN Order_item.oi_no is 'oi_no';
COMMENT ON COLUMN Order_item.oi_qty is 'oi_qty';
COMMENT ON COLUMN Order_item.order_no is 'order_no';
COMMENT ON COLUMN Order_item.product_no is 'product_no';
COMMENT ON COLUMN Order_item.os_no is 'os_no';


/**********************************/
/* Table Name: Cart */
/**********************************/
CREATE TABLE Cart(
		cart_no                       		NUMBER(10)		 NULL ,
		cart_qty                      		NUMBER(10)		 NULL ,
		product_no                    		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Cart_cart_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Cart_cart_no_TRG
BEFORE INSERT ON Cart
FOR EACH ROW
BEGIN
IF :NEW.cart_no IS NOT NULL THEN
  SELECT Cart_cart_no_SEQ.NEXTVAL INTO :NEW.cart_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Cart is 'Cart';
COMMENT ON COLUMN Cart.cart_no is 'cart_no';
COMMENT ON COLUMN Cart.cart_qty is 'cart_qty';
COMMENT ON COLUMN Cart.product_no is 'product_no';
COMMENT ON COLUMN Cart.user_no is 'user_no';


/**********************************/
/* Table Name: MyPet */
/**********************************/
CREATE TABLE MyPet(
		mypet_no                      		NUMBER(10)		 NULL ,
		mypet_name                    		VARCHAR2(50)		 NULL ,
		mypet_birthday                		TIMESTAMP(9)		 NULL ,
		user_no                       		NUMBER(10)		 NULL ,
		mypet_kind                    		VARCHAR2(50)		 NULL 
);

CREATE SEQUENCE MyPet_mypet_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER MyPet_mypet_no_TRG
BEFORE INSERT ON MyPet
FOR EACH ROW
BEGIN
IF :NEW.mypet_no IS NOT NULL THEN
  SELECT MyPet_mypet_no_SEQ.NEXTVAL INTO :NEW.mypet_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE MyPet is 'MyPet';
COMMENT ON COLUMN MyPet.mypet_no is 'mypet_no';
COMMENT ON COLUMN MyPet.mypet_name is 'mypet_name';
COMMENT ON COLUMN MyPet.mypet_birthday is 'mypet_birthday';
COMMENT ON COLUMN MyPet.user_no is 'user_no';
COMMENT ON COLUMN MyPet.mypet_kind is 'mypet_kind';


/**********************************/
/* Table Name: ReviewBoard */
/**********************************/
CREATE TABLE ReviewBoard(
		board_no                      		NUMBER(10)		 NULL ,
		board_title                   		VARCHAR2(100)		 NULL ,
		board_content                 		VARCHAR2(100)		 NULL ,
		board_date                    		DATE		 NULL ,
		board_star                    		NUMBER(10)		 NULL ,
		product_no                    		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL ,
		oi_no                         		NUMBER(10)		 NULL 
);

CREATE SEQUENCE ReviewBoard_board_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER ReviewBoard_board_no_TRG
BEFORE INSERT ON ReviewBoard
FOR EACH ROW
BEGIN
IF :NEW.board_no IS NOT NULL THEN
  SELECT ReviewBoard_board_no_SEQ.NEXTVAL INTO :NEW.board_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE ReviewBoard is 'ReviewBoard';
COMMENT ON COLUMN ReviewBoard.board_no is 'board_no';
COMMENT ON COLUMN ReviewBoard.board_title is 'board_title';
COMMENT ON COLUMN ReviewBoard.board_content is 'board_content';
COMMENT ON COLUMN ReviewBoard.board_date is 'board_date';
COMMENT ON COLUMN ReviewBoard.board_star is 'board_star';
COMMENT ON COLUMN ReviewBoard.product_no is 'product_no';
COMMENT ON COLUMN ReviewBoard.user_no is 'user_no';
COMMENT ON COLUMN ReviewBoard.oi_no is 'oi_no';


/**********************************/
/* Table Name: ReplyBoard */
/**********************************/
CREATE TABLE ReplyBoard(
		reply_board_no                		NUMBER(10)		 NOT NULL,
		reply_board_register_date     		DATE		 NULL ,
		reply_board_content           		VARCHAR2(50)		 NULL ,
		reply_board_group_no          		NUMBER(10)		 NULL ,
		reply_board_step              		NUMBER(10)		 NULL ,
		reply_board_depth             		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL ,
		board_no                      		NUMBER(10)		 NULL 
);

CREATE SEQUENCE ReplyBoard_reply_board_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER ReplyBoard_reply_board_no_TRG
BEFORE INSERT ON ReplyBoard
FOR EACH ROW
BEGIN
IF :NEW.reply_board_no IS NOT NULL THEN
  SELECT ReplyBoard_reply_board_no_SEQ.NEXTVAL INTO :NEW.reply_board_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE ReplyBoard is 'ReplyBoard';
COMMENT ON COLUMN ReplyBoard.reply_board_no is 'reply_board_no';
COMMENT ON COLUMN ReplyBoard.reply_board_register_date is 'reply_board_register_date';
COMMENT ON COLUMN ReplyBoard.reply_board_content is 'reply_board_content';
COMMENT ON COLUMN ReplyBoard.reply_board_group_no is 'reply_board_group_no';
COMMENT ON COLUMN ReplyBoard.reply_board_step is 'reply_board_step';
COMMENT ON COLUMN ReplyBoard.reply_board_depth is 'reply_board_depth';
COMMENT ON COLUMN ReplyBoard.user_no is 'user_no';
COMMENT ON COLUMN ReplyBoard.board_no is 'board_no';


/**********************************/
/* Table Name: Wish */
/**********************************/
CREATE TABLE Wish(
		wish_no                       		NUMBER(10)		 NULL ,
		product_no                    		NUMBER(10)		 NULL ,
		user_no                       		NUMBER(10)		 NULL 
);

CREATE SEQUENCE Wish_wish_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER Wish_wish_no_TRG
BEFORE INSERT ON Wish
FOR EACH ROW
BEGIN
IF :NEW.wish_no IS NOT NULL THEN
  SELECT Wish_wish_no_SEQ.NEXTVAL INTO :NEW.wish_no FROM DUAL;
END IF;
END;

COMMENT ON TABLE Wish is 'Wish';
COMMENT ON COLUMN Wish.wish_no is 'wish_no';
COMMENT ON COLUMN Wish.product_no is 'product_no';
COMMENT ON COLUMN Wish.user_no is 'user_no';



ALTER TABLE UserInfo ADD CONSTRAINT IDX_UserInfo_PK PRIMARY KEY (user_no);

ALTER TABLE Coupon ADD CONSTRAINT IDX_Coupon_PK PRIMARY KEY (coupon_id);
ALTER TABLE Coupon ADD CONSTRAINT IDX_Coupon_FK0 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE ReportBoard ADD CONSTRAINT IDX_ReportBoard_PK PRIMARY KEY (board_no);
ALTER TABLE ReportBoard ADD CONSTRAINT IDX_ReportBoard_FK0 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE Local ADD CONSTRAINT IDX_Local_PK PRIMARY KEY (local_no);

ALTER TABLE Center ADD CONSTRAINT IDX_Center_PK PRIMARY KEY (center_no);
ALTER TABLE Center ADD CONSTRAINT IDX_Center_FK0 FOREIGN KEY (local_no) REFERENCES Local (local_no);

ALTER TABLE Volunteer ADD CONSTRAINT IDX_Volunteer_PK PRIMARY KEY (volunteer_no);
ALTER TABLE Volunteer ADD CONSTRAINT IDX_Volunteer_FK0 FOREIGN KEY (center_no) REFERENCES Center (center_no);
ALTER TABLE Volunteer ADD CONSTRAINT IDX_Volunteer_FK1 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE Visit ADD CONSTRAINT IDX_Visit_PK PRIMARY KEY (visit_no);
ALTER TABLE Visit ADD CONSTRAINT IDX_Visit_FK0 FOREIGN KEY (center_no) REFERENCES Center (center_no);
ALTER TABLE Visit ADD CONSTRAINT IDX_Visit_FK1 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE Pet ADD CONSTRAINT IDX_Pet_PK PRIMARY KEY (pet_no);
ALTER TABLE Pet ADD CONSTRAINT IDX_Pet_FK0 FOREIGN KEY (center_no) REFERENCES Center (center_no);
ALTER TABLE Pet ADD CONSTRAINT IDX_Pet_FK1 FOREIGN KEY (local_no) REFERENCES Local (local_no);

ALTER TABLE Adopt ADD CONSTRAINT IDX_Adopt_PK PRIMARY KEY (adopt_no);
ALTER TABLE Adopt ADD CONSTRAINT IDX_Adopt_FK0 FOREIGN KEY (pet_no) REFERENCES Pet (pet_no);
ALTER TABLE Adopt ADD CONSTRAINT IDX_Adopt_FK1 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE Product ADD CONSTRAINT IDX_Product_PK PRIMARY KEY (product_no);

ALTER TABLE Orders ADD CONSTRAINT IDX_Orders_PK PRIMARY KEY (order_no);
ALTER TABLE Orders ADD CONSTRAINT IDX_Orders_FK0 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE OrderStatus ADD CONSTRAINT IDX_OrderStatus_PK PRIMARY KEY (os_no);

ALTER TABLE Order_item ADD CONSTRAINT IDX_Order_item_PK PRIMARY KEY (oi_no);
ALTER TABLE Order_item ADD CONSTRAINT IDX_Order_item_FK0 FOREIGN KEY (order_no) REFERENCES Orders (order_no);
ALTER TABLE Order_item ADD CONSTRAINT IDX_Order_item_FK1 FOREIGN KEY (product_no) REFERENCES Product (product_no);
ALTER TABLE Order_item ADD CONSTRAINT IDX_Order_item_FK2 FOREIGN KEY (os_no) REFERENCES OrderStatus (os_no);

ALTER TABLE Cart ADD CONSTRAINT IDX_Cart_PK PRIMARY KEY (cart_no);
ALTER TABLE Cart ADD CONSTRAINT IDX_Cart_FK0 FOREIGN KEY (product_no) REFERENCES Product (product_no);
ALTER TABLE Cart ADD CONSTRAINT IDX_Cart_FK1 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE MyPet ADD CONSTRAINT IDX_MyPet_PK PRIMARY KEY (mypet_no);
ALTER TABLE MyPet ADD CONSTRAINT IDX_MyPet_FK0 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

ALTER TABLE ReviewBoard ADD CONSTRAINT IDX_ReviewBoard_PK PRIMARY KEY (board_no);
ALTER TABLE ReviewBoard ADD CONSTRAINT IDX_ReviewBoard_FK0 FOREIGN KEY (product_no) REFERENCES Product (product_no);
ALTER TABLE ReviewBoard ADD CONSTRAINT IDX_ReviewBoard_FK1 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);
ALTER TABLE ReviewBoard ADD CONSTRAINT IDX_ReviewBoard_FK2 FOREIGN KEY (oi_no) REFERENCES Order_item (oi_no);

ALTER TABLE ReplyBoard ADD CONSTRAINT IDX_ReplyBoard_PK PRIMARY KEY (reply_board_no);
ALTER TABLE ReplyBoard ADD CONSTRAINT IDX_ReplyBoard_FK0 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);
ALTER TABLE ReplyBoard ADD CONSTRAINT IDX_ReplyBoard_FK1 FOREIGN KEY (board_no) REFERENCES ReportBoard (board_no);

ALTER TABLE Wish ADD CONSTRAINT IDX_Wish_PK PRIMARY KEY (wish_no);
ALTER TABLE Wish ADD CONSTRAINT IDX_Wish_FK0 FOREIGN KEY (product_no) REFERENCES Product (product_no);
ALTER TABLE Wish ADD CONSTRAINT IDX_Wish_FK1 FOREIGN KEY (user_no) REFERENCES UserInfo (user_no);

