CREATE TABLE IF NOT EXISTS booking_business (
    id uuid NOT NULL,
    booking_date timestamptz NOT NULL,
    user_id uuid NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES worknest_user(id)
);

ALTER TABLE booking
ADD booking_business_id uuid,
ADD CONSTRAINT booking_business_fkey FOREIGN KEY (booking_business_id) REFERENCES booking_business(id);