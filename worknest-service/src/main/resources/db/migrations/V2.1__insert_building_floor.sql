CREATE TABLE IF NOT EXISTS building (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    province varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    street_number int NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS floor (
    id uuid NOT NULL,
    number_of_floor int NOT NULL,
    num_bathrooms integer NOT NULL,
    building_id uuid NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (building_id) REFERENCES building(id)
);


ALTER TABLE workstation
DROP COLUMN floor,
ADD cx float,
ADD cy float,
ADD number_of_seats int,
ADD is_left_position boolean,
ADD is_present_window boolean,
ADD floor_id uuid,
ADD building_id uuid;

ALTER TABLE workstation
ADD CONSTRAINT floor_fkey FOREIGN KEY (floor_id) REFERENCES floor(id),
ADD CONSTRAINT building_fkey FOREIGN KEY (building_id) REFERENCES building(id);