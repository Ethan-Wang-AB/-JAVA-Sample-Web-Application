
INSERT INTO `absdb`.`event` VALUES (1, 'Calgary event','www.youtube.com/calgary');
INSERT INTO `event` VALUES (2, 'Lethbridge event','www.youtube.com/lethbridge');
INSERT INTO `event` VALUES (3, 'Edmonton event','www.youtube.com/edmonton');


INSERT INTO `state` VALUES (1, 'AB');

INSERT INTO `city` VALUES (1, 'Calgary',1);
INSERT INTO `city` VALUES (2, 'Lethbridge',1);
INSERT INTO `city` VALUES (3, 'Edmonton',1);

INSERT INTO `address` VALUES (1, 'Calgary downtown',1);
INSERT INTO `address` VALUES (2, 'Lethbridge downtown',2);
INSERT INTO `address` VALUES (3, 'Edmonton downtown',3);

INSERT INTO `diet` VALUES (1, 'no sugar');
INSERT INTO `diet` VALUES (2, 'no meat');
INSERT INTO `diet` VALUES (3, 'gluton free');

INSERT INTO `affiliation` VALUES (1, 'Liberal');
INSERT INTO `affiliation` VALUES (2, 'Conservative');

INSERT INTO `role` VALUES (1, 'Admin','hold of the event and the owner of the company',0);
INSERT INTO `role` VALUES (2, 'Technician','Check the bike',1);
INSERT INTO `role` VALUES (3, 'Technician','check the bike',2);
INSERT INTO `role` VALUES (4, 'Technician','check the bike',3);
INSERT INTO `role` VALUES (5, 'Food Provider','Provide food',4);
INSERT INTO `role` VALUES (6, 'Seller','Sell bike',-1);
INSERT INTO `role` VALUES (7, 'Buyer','Buy bike',-1);

INSERT INTO `faq` VALUES (1, '1st question','who is the owner','Laura',1,1,1);
INSERT INTO `faq` VALUES (2, '2nd question','where is the event','3 cities',1,1,2);
INSERT INTO `faq` VALUES (3, '3rd question','will it happen in 2022','not sure',1,1,3);

INSERT INTO `safety` VALUES (1, 'Fire safety','Important to take care of','www.safety.com',1,1,1);
INSERT INTO `safety` VALUES (2, 'Theft','Take care of your money','www.call311.com',1,1,2);
INSERT INTO `safety` VALUES (3, 'Extreme Weather Notice','Be healthy','www.weather.com',1,1,3);


INSERT INTO `event_date` VALUES (1, 1,1,'2022-10-22','2022-10-24','day 1 collect, day2 sell');
INSERT INTO `event_date` VALUES (2, 2,2,'2022-10-22','2022-10-24','day 1 collect, day2 sell');
INSERT INTO `event_date` VALUES (3, 3,3,'2022-10-22','2022-10-24','day 1 collect, day2 sell');


INSERT INTO `user` (`email`,`name`,`password`,`role_id`,`cell_number`,`address_id`,`diet_id`,`shirt_size`,affiliation_id)
	VALUES ('cprg352.test.sait+admin@gmail.com','admin', '12345678', 1,'4036781234',1,1,1,1);
	
INSERT INTO `user` (`email`,`name`,`password`,`role_id`,`cell_number`,`address_id`,`diet_id`,`shirt_size`,affiliation_id,ticket)
	VALUES ('cprg352.test.sait+seller2@gmail.com','seller', '12345678', 6,'4036781234',1,1,1,1,1123456789);
	


INSERT INTO `user` (`email`,`name`,`password`,`role_id`,`cell_number`,`address_id`,`diet_id`,`shirt_size`,affiliation_id)
	VALUES ('cprg352.test.sait+buyer@gmail.com','buyer', '12345678', 7,'4036781234',1,1,1,1);

INSERT INTO `user` (`email`,`name`,`password`,`role_id`,`cell_number`,`address_id`,`diet_id`,`shirt_size`,affiliation_id)
	VALUES ('cprg352.test.sait+technician@gmail.com','technician', '12345678', 3,'4036781234',1,1,1,1);
	
INSERT INTO `user` (`email`,`name`,`password`,`role_id`,`cell_number`,`address_id`,`diet_id`,`shirt_size`,affiliation_id)
	VALUES ('cprg352.test.sait+foodprovider@gmail.com','food provider', '12345678', 5,'4036781234',1,1,1,1);





INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (1,1);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (2,1);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (3,1);


INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (1,2);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (1,3);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (1,4);


INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (2,3);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (2,4);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (2,5);

INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (3,2);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (3,3);
INSERT INTO `event_date_user` (`event_date_id`,`user_id`) VALUES (3,5);

INSERT INTO `bike` (`bike_index`,`user_id`,`price`,`toSell`,`donate`) 
   VALUES('123456789A',2,500.5,1,0);

INSERT INTO `bike` (`bike_index`,`user_id`,`price`,`toSell`,`donate`) 
   VALUES('123456789C',1,500.5,1,0);

INSERT INTO `bike` (`bike_index`,`user_id`,`price`,`toSell`,`donate`) 
   VALUES('123456789D',1,500.5,1,0);
   
INSERT INTO `bike` (`bike_index`,`user_id`,`price`,`toSell`,`donate`) 
   VALUES('B123456789',3,610.2,1,0);
   
INSERT INTO `trade` VALUES(1,2,3,'2022-5-30',2);

INSERT INTO `trade_desc` VALUES(1,'Ticket-Seller',3);

INSERT INTO `trade_desc` VALUES(2,'Storage-Seller',5);

INSERT INTO `trade_desc` VALUES(3,'Ticket-Buyer',3);

INSERT INTO `trade_others` VALUES(1,1,2);

INSERT INTO `trade_others` VALUES(2,2,2);

INSERT INTO `trade_others` VALUES(3,3,3);

INSERT INTO `announcement` VALUES(1,'1st announcement','This is a test',null,null,DEFAULT);

INSERT INTO `announcement` VALUES(2,'2st announcement','This is for testing',null,null,DEFAULT);