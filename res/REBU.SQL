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
  user_id			int,
  username		varchar(255),
  password		varchar(255),
  first_name		varchar(255),
  last_name		varchar(255),
  phone_number	varchar(255),
  email			varchar(255),
  date_of_birth	varchar(255),
  car_id			int
);

CREATE TABLE USER_NOTIFICATIONS(
  user_id		int,
  ride_id		int
);

CREATE TABLE RIDES_LIST(
  ride_id			int,
  passenger_id	int,
  driver_id		int,
  start_date		varchar(255),
  start_location	varchar(255),
  end_location	varchar(255),
  start_time		int,
  ride_status_id	int
);

CREATE TABLE RIDE_STATUS(
  ride_status_id			int,
  ride_status_description	varchar(255)
);

CREATE TABLE CAR_DETAILS(
  car_id			int
);

/*
 * TEST DATA
 */

INSERT INTO USER_ACCOUNT(user_id, username, password, first_name, last_name, phone_number, email, date_of_birth, car_id)
VALUES
       (1,'Kali','Ollie','Kali The Destroyer','Of Worlds','555-636-9921','destroyer@LOL.com', '122-919-9912',0),
       (2,'USERNAME','PASSWORD','Joe','Biden', '123-456-7890', 'Prez4Ev@LOL.com', '010-118-3735',1);

INSERT INTO USER_NOTIFICATIONS(user_id, ride_id)
VALUES
       (1,1),
       (2,1);

INSERT INTO RIDES_LIST(ride_id, passenger_id, driver_id, start_date, start_location, end_location, start_time, ride_status_id)
VALUES
       (1,1,2, '11-4-2019','Orlando','Miami',1345,1);

INSERT INTO RIDE_STATUS(ride_status_id, ride_status_description)
VALUES
       (0,'INACTIVE'),
       (1,'ACTIVE'),
       (2,'Cancelled by Driver'),
       (3,'Cancelled by Passenger');

INSERT INTO CAR_DETAILS(car_id)
VALUES
       (0),
       (1);
