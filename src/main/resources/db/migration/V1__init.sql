CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE financial_records (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(15, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    category VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    notes TEXT,
    created_by BIGINT,
    FOREIGN KEY (created_by) REFERENCES users (id)
);
