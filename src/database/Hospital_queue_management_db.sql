-- drop and recreate database
drop database if exists hospital_queue_management_db;

create database hospital_queue_management_db;
use hospital_queue_management_db;

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
    status enum('active', 'deactive') default 'active',
    created_at timestamp default current_timestamp
);

-- 3. user_profiles
create table user_profiles (
    profile_id int auto_increment primary key,
    user_id int not null,
    full_name varchar(100) not null,
    dob date null,
    age int default 0,
    blood_group enum('A+','A-','B+','B-','O+','O-','AB+','AB-','Unknown') default 'Unknown',
    gender enum('male','female','others','prefer not to say') default 'prefer not to say',
    role enum('patient','doctor','receptionist','admin') not null,
    contact_number varchar(15),
    address varchar(255),
    created_at timestamp default current_timestamp,
    constraint fk_profiles_to_users foreign key (user_id) references users(user_id) on delete cascade
);

-- 4. admins
create table admins (
    admin_id varchar(10) primary key,
    user_id int not null,
    username varchar(50) not null,
    created_at timestamp default current_timestamp
);

-- 5. patients
create table patients (
    patient_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    username varchar(50) not null,
    dob date null,
    age int default 0,
    blood_group enum('A+','A-','B+','B-','O+','O-','AB+','AB-','Unknown') default 'Unknown',
    gender enum('male','female','others','prefer not to say') default 'prefer not to say',
    contact_number varchar(15),
    address varchar(255),
    created_at timestamp default current_timestamp
);

-- 6. doctors
create table doctors (
    doctor_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    username varchar(50) not null,
    specialization varchar(100),
    department_id int not null,
    availability enum('available','unavailable') default 'available',
    created_at timestamp default current_timestamp
);

-- 7. security_questions
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

-- 8. appointments
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

-- 9. queue
create table queue (
    queue_id int auto_increment primary key,
    appointment_id int not null,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    token_number int not null,
    status enum('waiting','in consultation','completed') default 'waiting',
    created_at timestamp default current_timestamp
);

-- 10. waitlist
create table waitlist (
    waitlist_id int auto_increment primary key,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    reason varchar(255),
    status enum('waiting','moved to queue','cancelled') default 'waiting',
    created_at timestamp default current_timestamp
);

-- 11. medical_records
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

-- 12. ratings
create table ratings (
    rating_id int auto_increment primary key,
    appointment_id int not null,
    patient_id varchar(10) not null,
    doctor_id varchar(10) not null,
    stars int check (stars between 1 and 5),
    feedback text,
    created_at timestamp default current_timestamp
);

-- 13. receptionists
create table receptionists (
    receptionist_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    username varchar(50) not null,
    shift enum('morning','afternoon','evening') default 'morning',
    created_at timestamp default current_timestamp
);

-- =========================
-- FOREIGN KEYS (via ALTER)
-- =========================

alter table admins
add constraint fk_admins_to_users foreign key (user_id) references users(user_id) on delete cascade;

alter table patients
add constraint fk_patients_to_users foreign key (user_id) references users(user_id) on delete cascade;

alter table doctors
add constraint fk_doctors_to_users foreign key (user_id) references users(user_id) on delete cascade;

alter table doctors
add constraint fk_doctors_to_departments foreign key (department_id) references departments(department_id) on delete cascade;

alter table security_questions
add constraint fk_security_to_users foreign key (user_id) references users(user_id) on delete cascade;

alter table appointments
add constraint fk_appointments_to_patients foreign key (patient_id) references patients(patient_id) on delete cascade;

alter table appointments
add constraint fk_appointments_to_doctors foreign key (doctor_id) references doctors(doctor_id) on delete cascade;

alter table queue
add constraint fk_queue_to_appointments foreign key (appointment_id) references appointments(appointment_id) on delete cascade;

alter table queue
add constraint fk_queue_to_patients foreign key (patient_id) references patients(patient_id) on delete cascade;

alter table queue
add constraint fk_queue_to_doctors foreign key (doctor_id) references doctors(doctor_id) on delete cascade;

alter table waitlist
add constraint fk_waitlist_to_patients foreign key (patient_id) references patients(patient_id) on delete cascade;

alter table waitlist
add constraint fk_waitlist_to_doctors foreign key (doctor_id) references doctors(doctor_id) on delete cascade;

alter table medical_records
add constraint fk_records_to_appointments foreign key (appointment_id) references appointments(appointment_id) on delete cascade;

alter table medical_records
add constraint fk_records_to_patients foreign key (patient_id) references patients(patient_id) on delete cascade;

alter table medical_records
add constraint fk_records_to_doctors foreign key (doctor_id) references doctors(doctor_id) on delete cascade;

alter table ratings
add constraint fk_ratings_to_appointments foreign key (appointment_id) references appointments(appointment_id) on delete cascade;

alter table ratings
add constraint fk_ratings_to_patients foreign key (patient_id) references patients(patient_id) on delete cascade;

alter table ratings
add constraint fk_ratings_to_doctors foreign key (doctor_id) references doctors(doctor_id) on delete cascade;

alter table receptionists
add constraint fk_receptionists_to_users foreign key (user_id) references users(user_id) on delete cascade;

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

-- dummy users (passwords are 'password' just for testing)
insert into users (username, password, status) values 
('super_admin', 'password', 'active'),
('system_admin', 'password', 'active'),
('ram_receptionist', 'password', 'active'),
('gita_receptionist', 'password', 'active'),
('sita_sharma', 'password', 'active'),
('hari_thapa', 'password', 'active'),
('anil_koirala', 'password', 'active'),
('doc_anil', 'password', 'active'),
('doc_meera', 'password', 'active'),
('doc_suresh', 'password', 'active'),
('doc_rupa', 'password', 'active'),
('doc_deepak', 'password', 'active');

-- dummy user_profiles
insert into user_profiles (user_id, full_name, dob, age, blood_group, gender, role, contact_number, address) values 
(1, 'Super Admin', null, 0, 'Unknown', 'prefer not to say', 'admin', '9800000100', 'Kathmandu'),
(2, 'System Admin', null, 0, 'Unknown', 'prefer not to say', 'admin', '9800000101', 'Kathmandu'),
(3, 'Ram Receptionist', null, 0, 'Unknown', 'prefer not to say', 'receptionist', '9800000003', 'Pokhara'),
(4, 'Gita Receptionist', null, 0, 'Unknown', 'prefer not to say', 'receptionist', '9800000020', 'Lalitpur'),
(5, 'Sita Sharma', '1995-04-12', 31, 'A+', 'female', 'patient', '9800000001', 'Kathmandu'),
(6, 'Hari Thapa', '1988-07-20', 38, 'O-', 'male', 'patient', '9800000002', 'Pokhara'),
(7, 'Anil Koirala', '2000-01-15', 26, 'B+', 'male', 'patient', '9800000004', 'Bhaktapur'),
(8, 'Dr. Anil Khatiwada', null, 0, 'Unknown', 'prefer not to say', 'doctor', '9811111111', 'Kathmandu'),
(9, 'Dr. Meera Shrestha', null, 0, 'Unknown', 'prefer not to say', 'doctor', '9811111112', 'Lalitpur'),
(10, 'Dr. Suresh Lama', null, 0, 'Unknown', 'prefer not to say', 'doctor', '9811111113', 'Pokhara'),
(11, 'Dr. Rupa Joshi', null, 0, 'Unknown', 'prefer not to say', 'doctor', '9811111114', 'Biratnagar'),
(12, 'Dr. Deepak Giri', null, 0, 'Unknown', 'prefer not to say', 'doctor', '9811111115', 'Butwal');

insert into admins (admin_id, user_id, username) values
('A-001', 1, 'super_admin'),
('A-002', 2, 'system_admin');

insert into receptionists (receptionist_id, user_id, full_name, username, shift) values
('R-001', 3, 'Ram Receptionist', 'ram_receptionist', 'morning'),
('R-002', 4, 'Gita Receptionist', 'gita_receptionist', 'afternoon');

insert into patients (patient_id, user_id, full_name, username, dob, age, blood_group, gender, contact_number, address) values
('P-001', 5, 'Sita Sharma', 'sita_sharma', '1995-04-12', 31, 'A+', 'female', '9800000001', 'Kathmandu'),
('P-002', 6, 'Hari Thapa', 'hari_thapa', '1988-07-20', 38, 'O-', 'male', '9800000002', 'Pokhara'),
('P-003', 7, 'Anil Koirala', 'anil_koirala', '2000-01-15', 26, 'B+', 'male', '9800000004', 'Bhaktapur');

insert into doctors (doctor_id, user_id, full_name, username, specialization, department_id, availability) values
('D-101', 8, 'Dr. Anil Khatiwada', 'doc_anil', 'Cardiologist', 1, 'available'),
('D-102', 9, 'Dr. Meera Shrestha', 'doc_meera', 'Neurologist', 2, 'available'),
('D-103', 10, 'Dr. Suresh Lama', 'doc_suresh', 'Orthopedic Surgeon', 3, 'available'),
('D-104', 11, 'Dr. Rupa Joshi', 'doc_rupa', 'General Physician', 4, 'available'),
('D-105', 12, 'Dr. Deepak Giri', 'doc_deepak', 'Pediatrician', 5, 'available');

use hospital_queue_management_db;
select * from admins;
select * from patients;
select * from users;
select * from doctors;
select * from user_profiles;