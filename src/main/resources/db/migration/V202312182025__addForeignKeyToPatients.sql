ALTER TABLE patients
ADD CONSTRAINT fk_department_id
FOREIGN KEY (department_id)
REFERENCES departments(id);