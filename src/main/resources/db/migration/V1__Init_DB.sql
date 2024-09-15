-- V1__Create_users_table.sql

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(100) UNIQUE,
                       phone VARCHAR(20),
                       date_of_birth DATE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);