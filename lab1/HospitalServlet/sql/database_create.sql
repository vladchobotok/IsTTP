DROP DATABASE IF EXISTS hospital_db;

CREATE DATABASE IF NOT EXISTS hospital_db;

USE hospital_db;

CREATE TABLE IF NOT EXISTS roles
(
   id                   INT PRIMARY KEY AUTO_INCREMENT,
   name                 NVARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS users
(
id             INT PRIMARY KEY AUTO_INCREMENT,
name 		   NVARCHAR(255),
surname        NVARCHAR(255),
birthday       DATE,
email          NVARCHAR(255),
password       VARCHAR(255) NOT NULL,
role_id        INT,
FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS doctors_type
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    type NVARCHAR(255)
);


CREATE TABLE IF NOT EXISTS doctors
(
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
doctor_type_id INT,
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ,
FOREIGN KEY(doctor_type_id) REFERENCES doctors_type(id)
);


CREATE TABLE IF NOT EXISTS assignments_type
(
 id INT PRIMARY KEY AUTO_INCREMENT,
 type NVARCHAR(255)
);


CREATE TABLE IF NOT EXISTS assignments
(
 id INT PRIMARY KEY AUTO_INCREMENT,
 executor_id INT,
 prescriber_id INT,
 description          NVARCHAR(500),
 assignment_type_id           INT,
 FOREIGN KEY(executor_id) REFERENCES doctors(id),
 FOREIGN KEY(prescriber_id) REFERENCES doctors(id),
 FOREIGN KEY(assignment_type_id) REFERENCES assignments_type(id)
);

CREATE TABLE IF NOT EXISTS diagnosis
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    type  NVARCHAR(255)
);

CREATE TABLE IF NOT EXISTS treatments
(
id             INT PRIMARY KEY AUTO_INCREMENT,
assignment_id INT,
diagnosis_id  INT,
FOREIGN KEY(assignment_id) REFERENCES assignments(id),
FOREIGN KEY(diagnosis_id) REFERENCES diagnosis(id)
);

CREATE TABLE IF NOT EXISTS patients
(
id    INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
doctor_id INT,
treatment_id INT,
FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
FOREIGN KEY(doctor_id) REFERENCES doctors(id),
FOREIGN KEY(treatment_id) REFERENCES treatments(id)
);


 INSERT INTO roles(id, name) VALUES(1,'PATIENT'),(2,'DOCTOR'),(3,'ADMIN'), (4, 'NURSE'), (5, 'CURED');

 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(1, 'Ivan', 'Ivanov','2000-01-01', 'patient1@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 1);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(2, 'Petro', 'Petrov','2000-02-02', 'patient2@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 1);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(3, 'Anton', 'Antonov','2000-03-03', 'patient3@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 1);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(4, 'Mariya', 'Makarova', '2000-03-03', 'nurse1@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 4);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(5, 'Anastasiya', 'Stepanova', '2000-01-03', 'nurse2@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 4);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(6, 'Olha', 'Havriliuk', '2000-11-03', 'nurse3@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 4);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(7, 'Olexiy', 'Komarov','2000-03-03', 'doctor1@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 2);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(8, 'Oleksandr', 'Voloshyn','2000-11-03', 'doctor2@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 2);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(9, 'Evhen', 'Morozov','2000-10-03', 'doctor3@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 2);
 INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(10, 'Admin', 'Admin','2000-03-03', 'admin@gmail.com','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', 3);
INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(11, 'Unset', 'doctor', '2000-11-03', 'nurse4@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 4);
INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(12, 'Oleg', 'Nepyyvoda', '2002-11-03', 'patient4@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 1);
INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(13, 'Maxym', 'Tkachuk', '2001-11-14', 'patient5@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 1);
INSERT INTO users(id, name, surname, birthday, email, password, role_id) VALUE(14, 'Volodymyr', 'Stepanyuk', '2003-07-28', 'patient6@gmail.com','0FFE1ABD1A08215353C233D6E009613E95EEC4253832A761AF28FF37AC5A150C', 1);

INSERT INTO doctors_type(id, type) VALUES(1,'Nurse'), (2, 'ENT'), (3, 'Surgeon'), (4, 'Traumatologist');

 INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(1,7,2);
 INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(2,8,3);
 INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(3,9,4);
 INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(4,4,1);
 INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(5,5,1);
 INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(6,6,1);
INSERT INTO doctors(id, user_id, doctor_type_id) VALUE(7,11,1);

 INSERT INTO assignments_type(id, type) VALUES(1,'Procedure'), (2, 'Operation'), (3, 'Medicines'), (4, 'Unset assignment type');

 INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(1, 1, 1, 'Procedure for Ivan Ivanov', 1);
 INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(2, 2, 2, 'Operation for Petro Petrov', 2);
 INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(3, 3, 3, 'Antibiotics for Anton Antonov', 3);
INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(4, 7, 7, 'Unset assignment', 4);
INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(5, 4, 1, 'Procedure for Oleg Nepyyvoda', 1);
INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(6, 2, 3, 'Operation for Maxym Tkachuk', 2);
INSERT INTO assignments(id, executor_id, prescriber_id, description, assignment_type_id) VALUE(7, 5, 1, 'Medicines for Volodymyr Stepanyuk', 3);

 INSERT INTO diagnosis(id, type) VALUES(1, 'Unset diagnosis'), (2,'End dislocation'), (3, 'Limb fracture'), (4, 'Otitis'), (5, 'Laryngitis');

 INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(1, 1, 5);
 INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(2, 2, 3);
 INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(3, 3, 2);
INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(4, 4, 1);
INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(5, 5, 4);
INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(6, 6, 3);
INSERT INTO treatments(id, assignment_id, diagnosis_id) VALUE(7, 7, 5);

 INSERT INTO patients(id, user_id, doctor_id, treatment_id) VALUE(1, 1, 1, 1);
 INSERT INTO patients(id, user_id, doctor_id, treatment_id) VALUE(2, 2, 2, 2);
 INSERT INTO patients(id, user_id, doctor_id, treatment_id) VALUE(3, 3, 3, 3);
INSERT INTO patients(id, user_id, doctor_id, treatment_id) VALUE(4, 12, 1, 5);
INSERT INTO patients(id, user_id, doctor_id, treatment_id) VALUE(5, 13, 3, 6);
INSERT INTO patients(id, user_id, doctor_id, treatment_id) VALUE(6, 14, 1, 7);
 
 
 
 




