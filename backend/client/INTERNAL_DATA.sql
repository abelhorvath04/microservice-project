DROP TABLE IF EXISTS measurement;

CREATE TABLE IF NOT EXISTS measurement
(
    id SERIAL PRIMARY KEY,
    humidity double precision NOT NULL,
    temperature double precision NOT NULL,
    "timestamp" timestamp(6) without time zone
);

DROP TABLE IF EXISTS sensor;

CREATE TABLE IF NOT EXISTS sensor
(
    id SERIAL PRIMARY KEY,
    active boolean NOT NULL,
    name character varying(255),
    type character varying(255)
);

-- Insert data into sensor table
INSERT INTO sensor (name, active, type) VALUES
  ('Sensor A', true, 'Temperature'),
  ('Sensor B', false, 'Humidity'),
  ('Sensor C', true, 'Humidity');

INSERT INTO measurement (timestamp, temperature, humidity) VALUES
  ('2024-11-01 06:00:00', 18.2, 40.2),
  ('2024-11-01 14:00:00', 30.5, 60.5),
  ('2024-11-01 20:00:00', 25.0, 50.0);