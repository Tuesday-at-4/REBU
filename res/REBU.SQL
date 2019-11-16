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
DROP TABLE NOTIFICATION_TYPES;

CREATE TABLE USER_ACCOUNT (
	user_id						int		AUTO_INCREMENT,
	user_name					varchar(255),
	user_password			varchar(255),
	first_name				varchar(255),
	last_name					varchar(255),
	phone_number			varchar(10),
	user_email				varchar(255),
	date_of_birth			varchar(10),
	credit_card				varchar(255)
);
	
CREATE TABLE USER_NOTIFICATIONS(
	user_id							int,
	notification_type		int,
	noitfication_text		varchar(255)
);

CREATE TABLE NOTIFICATION_TYPES(
	notification_type					int,
	notification_description	varchar(255)
);

INSERT INTO NOTIFICATION_TYPES(notification_type, notification_description)
VALUES
			 (0, 'Admin'),
			 (1, 'Driver'),
			 (2, 'Passenger');

CREATE TABLE RIDES_LIST(
	ride_id					int		AUTO_INCREMENT,
	passenger_id		int,
	driver_id				int,
	start_time			varchar(255),
	start_date			varchar(255),
	start_location	varchar(255),
	end_location		varchar(255),
	ride_status_id	int
);

CREATE TABLE RIDE_STATUS(
	ride_status_id						int,
	ride_status_description		varchar(255)
);

INSERT INTO RIDE_STATUS(ride_status_id, ride_status_description)
VALUES
			 (0,'Accepted'),
			 (1,'Pending'),
			 (2,'Completed'),
			 (3,'Expired'),
			 (4,'Cancelled by Driver'),
			 (5,'Cancelled by Passenger');

CREATE TABLE CAR_DETAILS(
  car_id            int   auto_increment,
	user_id						int,
	car_manufacturer	varchar(255),
	car_model					varchar(255),
	car_year					int,
	car_type					varchar(255),
	license_plate			varchar(255),
	car_color					varchar(255),
	car_seating				int
);
