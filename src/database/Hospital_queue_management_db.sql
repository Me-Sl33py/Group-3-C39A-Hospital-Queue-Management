-- drop and recreate database
-- drop database if exists hospital_queue_management_db;

create database hospital_queue_management_db;
use hospital_queue_management_db;

-- create admins table
create table admins (
    admin_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    contact_number varchar(15),
    created_at timestamp default current_timestamp
);
-- 1. departments
create table departments (
    department_id int auto_increment primary key,
    department_name varchar(100) not null,
    description varchar(255),
    created_at timestamp default current_timestamp
);

-- 2. users
create table users (
    user_id int auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(255) not null,
    role enum('patient','doctor','receptionist','admin') not null,
    status enum('active', 'deactive') default 'active',
    created_at timestamp default current_timestamp
);

-- 3. patients
create table patients (
    patient_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    dob date null,
    age int not null,
    blood_group enum('A+','A-','B+','B-','O+','O-','AB+','AB-','Unknown') default 'Unknown',
    gender enum('male','female','others','prefer not to say') not null,
    contact_number varchar(10) not null,
    address varchar(255),
    created_at timestamp default current_timestamp
);

-- 4. doctors
create table doctors (
    doctor_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    specialization varchar(100),
    department_id int not null,
    contact_number varchar(10),
    availability enum('available','unavailable') default 'available',
    status enum('active', 'deactive') default 'active',
    created_at timestamp default current_timestamp
);

-- 5. security_questions
create table security_questions (
    question_id int auto_increment primary key,
    user_id int not null,
    question_1 varchar(255) not null,
    answer_1 varchar(255) not null,
    question_2 varchar(255) not null,
    answer_2 varchar(255) not null,
    question_3 varchar(255) not null,
    answer_3 varchar(255) not null,
    question_4 varchar(255) not null,
    answer_4 varchar(255) not null,
    question_5 varchar(255) not null,
    answer_5 varchar(255) not null
);

-- 6. appointments
create table appointments (
    appointment_id int auto_increment primary key,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    appointment_date date not null,
    appointment_time time not null,
    reason varchar(255),
    status enum('pending','confirmed','completed','cancelled') default 'pending',
    type enum('online','walk-in') default 'online',
    created_at timestamp default current_timestamp
);

-- 7. queue
create table queue (
    queue_id int auto_increment primary key,
    appointment_id int not null,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    token_number int not null,
    status enum('waiting','in consultation','completed') default 'waiting',
    created_at timestamp default current_timestamp
);

-- 8. waitlist
create table waitlist (
    waitlist_id int auto_increment primary key,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    reason varchar(255),
    status enum('waiting','moved to queue','cancelled') default 'waiting',
    created_at timestamp default current_timestamp
);

-- 9. medical_records
create table medical_records (
    record_id int auto_increment primary key,
    appointment_id int not null,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    diagnosis text,
    prescription text,
    notes text,
    created_at timestamp default current_timestamp
);

-- 10. ratings
create table ratings (
    rating_id int auto_increment primary key,
    appointment_id int not null,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    stars int check (stars between 1 and 5),
    feedback text,
    created_at timestamp default current_timestamp
);

-- 11. receptionists
create table receptionists (
    receptionist_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    contact_number varchar(10),
    shift enum('morning','afternoon','evening') default 'morning',
    created_at timestamp default current_timestamp
);

-- =========================
-- FOREIGN KEYS (via ALTER)
-- =========================

alter table admins
add constraint fk_admins_to_users
foreign key (user_id) references users(user_id);

alter table patients
add constraint fk_patients_to_users foreign key (user_id) references users(user_id);

alter table doctors
add constraint fk_doctors_to_users foreign key (user_id) references users(user_id);

alter table doctors
add constraint fk_doctors_to_departments foreign key (department_id) references departments(department_id);

alter table security_questions
add constraint fk_security_to_users foreign key (user_id) references users(user_id);

alter table appointments
add constraint fk_appointments_to_patients foreign key (patient_id) references patients(patient_id);

alter table appointments
add constraint fk_appointments_to_doctors foreign key (doctor_id) references doctors(doctor_id);

alter table queue
add constraint fk_queue_to_appointments foreign key (appointment_id) references appointments(appointment_id);

alter table queue
add constraint fk_queue_to_patients foreign key (patient_id) references patients(patient_id);

alter table queue
add constraint fk_queue_to_doctors foreign key (doctor_id) references doctors(doctor_id);

alter table waitlist
add constraint fk_waitlist_to_patients foreign key (patient_id) references patients(patient_id);

alter table waitlist
add constraint fk_waitlist_to_doctors foreign key (doctor_id) references doctors(doctor_id);

alter table medical_records
add constraint fk_records_to_appointments foreign key (appointment_id) references appointments(appointment_id);

alter table medical_records
add constraint fk_records_to_patients foreign key (patient_id) references patients(patient_id);

alter table medical_records
add constraint fk_records_to_doctors foreign key (doctor_id) references doctors(doctor_id);

alter table ratings
add constraint fk_ratings_to_appointments foreign key (appointment_id) references appointments(appointment_id);

alter table ratings
add constraint fk_ratings_to_patients foreign key (patient_id) references patients(patient_id);

alter table ratings
add constraint fk_ratings_to_doctors foreign key (doctor_id) references doctors(doctor_id);

alter table receptionists
add constraint fk_receptionists_to_users foreign key (user_id) references users(user_id);

-- =========================
-- SEED DATA
-- =========================

-- departments
insert into departments (department_name, description) values
('cardiology', 'heart and vascular diseases'),
('neurology', 'brain and nervous system'),
('orthopedics', 'bones and joints'),
('general medicine', 'general health checkup'),
('pediatrics', 'children health');

-- admin users
insert into users (username, password, role) values
('admin1', 'admin123', 'admin'),
('admin2', 'admin123', 'admin');

-- seed admin profiles
insert into admins (admin_id, user_id, full_name, contact_number) values
('A-001', 1, 'Super Admin', '9800000100'),
('A-002', 2, 'System Admin', '9800000101');

-- receptionists
insert into users (username, password, role) values
('reception1', 'reception123', 'receptionist'),
('reception2', 'reception123', 'receptionist');

insert into receptionists (receptionist_id, user_id, full_name, contact_number, shift) values
('R-001', 3, 'Ram Receptionist', '9800000003', 'morning'),
('R-002', 4, 'Gita Receptionist', '9800000020', 'afternoon');

-- patients
insert into users (username, password, role) values
('patient1', 'patient123', 'patient'),
('patient2', 'patient123', 'patient'),
('patient3', 'patient123', 'patient');

insert into patients (patient_id, user_id, full_name, dob, age, blood_group, gender, contact_number, address) values
('P-001', 5, 'Sita Sharma', '1995-04-12', 31, 'A+', 'female', '9800000001', 'Kathmandu'),
('P-002', 6, 'Hari Thapa', '1988-07-20', 38, 'O-', 'male', '9800000002', 'Lalitpur'),
('P-003', 7, 'Anil Koirala', '2000-01-15', 26, 'B+', 'male', '9800000004', 'Bhaktapur');

-- doctors (covering all departments)
insert into users (username, password, role) values
('doctor_cardiology', 'doctor123', 'doctor'),
('doctor_neurology', 'doctor123', 'doctor'),
('doctor_orthopedics', 'doctor123', 'doctor'),
('doctor_general', 'doctor123', 'doctor'),
('doctor_pediatrics', 'doctor123', 'doctor');

insert into doctors (doctor_id, user_id, full_name, specialization, department_id, contact_number, availability) values
('D-101', 8, 'Dr. Anil Khatiwada', 'Cardiologist', 1, '9811111111', 'available'),
('D-102', 9, 'Dr. Meera Shrestha', 'Neurologist', 2, '9811111112', 'available'),
('D-103', 10, 'Dr. Suresh Lama', 'Orthopedic Surgeon', 3, '9811111113', 'available'),
('D-104', 11, 'Dr. Rupa Joshi', 'General Physician', 4, '9811111114', 'available'),
('D-105', 12, 'Dr. Deepak Giri', 'Pediatrician', 5, '9811111115', 'available');

use hospital_queue_management_db;

describe patients;
select * from patients;
select * from users;
select * from doctors;
select * from receptionists;
select * from admins;
select * from security_questions;
select * from appointments;