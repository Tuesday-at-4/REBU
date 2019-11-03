/**
 * Michael Carracino
 * 11/1/2019
 *         The framework for the relational database for the REBU project
 *         NOTE: Dates, phone numbers, and Times are being stored as int for now
 */

//DROP TABLE USER_ACCOUNT;
//DROP TABLE USER_NOTIFICATIONS;
//DROP TABLE RIDES_LIST;
//DROP TABLE RIDE_STATUS;
//DROP TABLE CAR_DETAILS;

CREATE TABLE USER_ACCOUNT
(
    user_id       int primary key auto_increment,
    username      varchar(255),
    password      varchar(255),
    first_name    varchar(255),
    last_name     varchar(255),
    phone_number  int,
    email         varchar(255),
    date_of_birth int,
    car_id        int,
);

CREATE TABLE USER_NOTIFICATIONS
(
    notification_id int primary key auto_increment,
    user_id int,
    ride_id int
);

CREATE TABLE RIDES_LIST
(
    passenger_id   int,
    driver_id      int,
    start_date     int,
    start_location varchar(255),
    end_location   varchar(255),
    start_time     int,
    ride_id        int primary key auto_increment,
    ride_status_id int
);

CREATE TABLE RIDE_STATUS
(
    ride_status_id          int primary key auto_increment,
    ride_status_description varchar(255)
);

CREATE TABLE CAR_DETAILS
(
    car_id int primary key auto_increment
);


INSERT INTO USER_ACCOUNT(username, password, first_name, last_name, phone_number, email,
                         date_of_birth, car_id)
VALUES ('xX_Haxor_Xx', 'password', 'Sally', 'Draper', 1234567890, 'ChandelureRocks@gmail.com',
        10121973, 0);