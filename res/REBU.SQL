/**
 * Michael Carracino
 * 11/1/2019
 * 		The framework for the relational database for the REBU project
 * 		NOTE: Dates, phone numbers, and Times are being stored as int for now
 */

DROP TABLE USER_ACCOUNT;
DROP TABLE USER_NOTIFICATIONS;
DROP TABLE RIDES_LIST;
DROP TABLE RIDE_STATUS;
DROP TABLE CAR_DETAILS;

CREATE TABLE USER_ACCOUNT (
	user_id						int,
	user_name					varchar(255),
	user_password			varchar(255),
	first_name				varchar(255),
	last_name					varchar(255),
	phone_number			varchar(255),
	user_email				varchar(255),
	date_of_birth			varchar(255),
	car_id_one				int,
	car_id_two				int
);
	
CREATE TABLE USER_NOTIFICATIONS(
	user_id		int,
	ride_id		int
);

CREATE TABLE RIDES_LIST(
	ride_id					int,
	passenger_id		int,
	driver_id				int,
	start_date			varchar(255),
	start_location	varchar(255),
	end_location		varchar(255),
	start_time			varchar(255),
	ride_status_id	int
);

CREATE TABLE RIDE_STATUS(
	ride_status_id						int,
	ride_status_description		varchar(255)
);

CREATE TABLE CAR_DETAILS(
	car_id						int,
	car_manufacturer	varchar(255),
	car_model					varchar(255),
	car_year					int,
	car_type					varchar(255),
	license_plate			varchar(255),
	car_color					varchar(255),
	car_seating				int
);

/* 
 * TEST DATA
 */

INSERT INTO USER_ACCOUNT(user_id, user_name, user_password, first_name, last_name, phone_number, user_email, date_of_birth, car_id_one,car_id_two)
VALUES
	(1,'Kali','Ollie','Kali The Destroyer','Of Worlds','555-636-9921','destroyer@LOL.com', '122-919-9912',0,0),
	(2,'USERNAME','PASSWORD','Joe','Biden', '123-456-7890', 'Prez4Ev@LOL.com', '010-118-3735',1,0);

INSERT INTO USER_NOTIFICATIONS(user_id, ride_id)
VALUES
	(1,1),
	(2,1);

INSERT INTO RIDES_LIST(ride_id, passenger_id, driver_id, start_date, start_location, end_location, start_time, ride_status_id)
VALUES
	(1,1,2, '2019-04-11','Orlando','Miami','13:45',1);

	INSERT INTO RIDE_STATUS(ride_status_id, ride_status_description)
VALUES
	(0,'Accepted'),
	(1,'Pending'),
	(2,'Completed'),
	(3,'Expired'),
	(4,'Cancelled by Driver'),
	(5,'Cancelled by Passenger');
	
INSERT INTO CAR_DETAILS(car_id, car_manufacturer, car_model, car_year, car_type, license_plate, car_color, car_seating)
VALUES
	(0, 'NO_CAR', 'NO_CAR', 0, 'NO_CAR', 'NO_CAR','NO_CAR',0),
	(1, 'Chevy', 'Camaro', 1990, 'Coupe', 'A9H1E', 'NO_CAR',0);
