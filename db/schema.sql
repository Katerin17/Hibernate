CREATE TABLE car_model (
        id SERIAL PRIMARY KEY,
        name TEXT
);
CREATE TABLE car_brand (
        id SERIAL PRIMARY KEY,
        name TEXT
);
CREATE TABLE engines (
        id SERIAL PRIMARY KEY,
        model TEXT
);
CREATE TABLE cars(
        id SERIAL PRIMARY KEY,
        name TEXT,
        engine_id int not null unique references engines(id)
);
CREATE TABLE drivers(
        id SERIAL PRIMARY KEY,
        name TEXT
);
CREATE TABLE car_driver(
        id SERIAL PRIMARY KEY,
        car_id int not null references drivers(id),
        driver_id int not null references cars(id)
);
