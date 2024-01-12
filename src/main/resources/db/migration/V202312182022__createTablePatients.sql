CREATE TABLE patients(
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(8) NOT NULL,
    diagnosis VARCHAR(100),
    date_of_admission DATE NOT NULL,
    date_of_discharge DATE,
    department_id BIGINT
);