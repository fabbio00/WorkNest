CREATE TABLE IF NOT EXISTS company (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    vat_code varchar(50) NOT NULL,
    email varchar(255) NOT NULL,
    phone varchar(50) NOT NULL,
    company_code varchar(50) NOT NULL,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "user" (
	id uuid NOT NULL,
	name varchar(30) NOT NULL,
	surname varchar(30) NOT NULL,
	email varchar(30) NOT NULL,
	tax_code varchar(50) NOT NULL,
	type varchar(30) NOT NULL,
	barrier_free_flag boolean,
	registration_date timestamptz NOT NULL,
	status varchar(15),
	username varchar(30),
	company_id uuid,
	CONSTRAINT user_pkey PRIMARY KEY (id),
	CONSTRAINT company_fkey FOREIGN KEY(company_id) REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS user_info (
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    bio varchar(255),
    picture bytea,
    stack varchar(255),
    CONSTRAINT user_info_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE IF NOT EXISTS workstation (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    price_per_h decimal NOT NULL,
    equipment varchar(255) NOT NULL,
    type varchar(30) NOT NULL,
    floor integer NOT NULL,
    CONSTRAINT workstation_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS booking (
    id uuid NOT NULL,
    start_date timestamptz NOT NULL,
    end_date timestamptz NOT NULL,
    check_in timestamptz,
    check_out timestamptz,
    status varchar(50) NOT NULL,
    has_penalty boolean,
    user_id uuid NOT NULL,
    workstation_id uuid NOT NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT workstation_fkey FOREIGN KEY (workstation_id) REFERENCES workstation(id)
);

CREATE TABLE IF NOT EXISTS economic_transaction (
    id uuid NOT NULL,
    creation_date timestamptz NOT NULL,
    price decimal NOT NULL,
    amount_with_vat decimal NOT NULL,
    status varchar(50) NOT NULL,
    economic_transaction_type varchar(50) NOT NULL,
    user_id uuid,
    company_id uuid,
    CONSTRAINT economic_transaction_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT company_fkey FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS payment (
    id uuid NOT NULL,
    payment_date timestamptz NOT NULL,
    amount decimal NOT NULL,
    currency_type varchar(3) NOT NULL,
    brand varchar(50) NOT NULL,
    transaction_code varchar(50) NOT NULL,
    result_code varchar(50),
    result varchar(50),
    economic_transaction_id uuid,
    CONSTRAINT payment_pkey PRIMARY KEY (id),
    CONSTRAINT economic_transaction_fkey FOREIGN KEY (economic_transaction_id) REFERENCES economic_transaction(id)
);

CREATE TABLE IF NOT EXISTS contract (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    start_date timestamptz NOT NULL,
    end_date timestamptz NOT NULL,
    discount decimal,
    company_id uuid NOT NULL,
    CONSTRAINT contract_pkey PRIMARY KEY (id),
    CONSTRAINT company_fkey FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS friend_relationship (
    user_id1 uuid NOT NULL,
    user_id2 uuid NOT NULL,
    CONSTRAINT friend_relationship_pkey PRIMARY KEY (user_id1, user_id2),
    CONSTRAINT user_fkey1 FOREIGN KEY (user_id1) REFERENCES "user"(id),
    CONSTRAINT user_fkey2 FOREIGN KEY (user_id2) REFERENCES "user"(id)
);

CREATE TABLE IF NOT EXISTS ticket (
    id uuid NOT NULL,
    creation_date timestamptz NOT NULL,
    status varchar(50) NOT NULL,
    subject varchar(255) NOT NULL,
    description varchar(255),
    priority integer NOT NULL,
    user_id uuid NOT NULL,
    owner_user_id uuid,
    CONSTRAINT ticket_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES "user"(id),
    CONSTRAINT owner_user_fkey FOREIGN KEY (owner_user_id) REFERENCES "user"(id)
);
