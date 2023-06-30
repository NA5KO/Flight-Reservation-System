CREATE DATABASE FRS;

DROP TABLE IF EXISTS flight;
DROP TABLE IF EXISTS passenger;
DROP TABLE IF EXISTS reservation;

CREATE TABLE flight (
  flight_id SERIAL PRIMARY KEY,
  destination varchar(255) NOT NULL,
  departure TIMESTAMP NOT NULL,
  arrival TIMESTAMP NOT NULL,
  seats_number int NOT NULL,
  stopover varchar(255),
  stoptime int,
  fstatus varchar(255),
  ariline varchar(255),
  aircraft varchar(255)
);

CREATE TABLE passenger (
  passport_id int NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  pass varchar(255) NOT NULL,
  nationality varchar(255) NOT NULL,
  age int NOT NULL,
  phone_number int NOT NULL,
  email varchar(50),
  bank_account float,
  PRIMARY KEY (passport_id)
);

CREATE TABLE reservation (
  reservation_id SERIAL PRIMARY KEY,
  passport_id int NOT NULL,
  flight_id int NOT NULL,
  seat_id int NOT NULL,
  class varchar(255) NOT NULL,
  price float NOT NULL,
  FOREIGN KEY (passport_id) REFERENCES passenger (passport_id),
  FOREIGN KEY (flight_id) REFERENCES flight (flight_id)
);
