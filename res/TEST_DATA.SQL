INSERT INTO USER_ACCOUNT(user_name,	user_password,	first_name, last_name,	phone_number,	user_email, date_of_birth,	credit_card)
VALUES
       ('Kali', 'Ollie', 'Kali', 'The Destroyer', '0000000000', 'Meow@Yahoo.com', '2002-11-08', '1234567891011'),
       ('USERNAME', 'PASSWORD', 'Old', 'Joe', '3349123413', 'Dummy@Yahoo.com', '1723-10-22', '0034567891011');

INSERT INTO USER_NOTIFICATIONS(user_id, notification_type, notification_text)
VALUES
       (1, 2, 'Your Ride was accepted!'),
       (2, 1, 'You accepted a ride!'),
       (3, 4, 'User: 2 changed Ride #7 status from active " to: " + completed');

INSERT INTO RIDES_LIST(passenger_id, driver_id, start_time, start_date, start_location, end_location, ride_status_id)
VALUES
       (1, 0, '13:12:34', '2019-11-12', 'Miami', 'Orlando', 1),
       (1, 2, '08:12:02', '2019-12-13','Gainesville', 'Butte', 0);

INSERT INTO CAR_DETAILS(user_id, car_manufacturer, car_model, car_year, car_type, license_plate, car_color, car_seating)
VALUES
       (1,'Toyota','Sedan', 1764, '4-wheel', 'QTK19FS', 'Blue',7);
