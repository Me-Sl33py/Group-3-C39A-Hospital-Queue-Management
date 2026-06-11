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
    role enum('patient','doctor','receptionist','admin') not null,
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
    contact_number varchar(15),
    address varchar(255),
    created_at timestamp default current_timestamp,
    constraint fk_profiles_to_users foreign key (user_id) references users(user_id) on delete cascade
);

-- 4. admins
create table admins (
    admin_id varchar(10) primary key,
    user_id int not null,
    created_at timestamp default current_timestamp
);

-- 5. patients
create table patients (
    patient_id varchar(10) primary key,
    user_id int not null,
    created_at timestamp default current_timestamp
);

-- 6. doctors
create table doctors (
    doctor_id varchar(10) primary key,
    user_id int not null,
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

-- admin users
insert into users (username, password, role) values
('admin1', 'admin123', 'admin'),
('admin2', 'admin123', 'admin');

-- seed admin profiles
insert into user_profiles (user_id, full_name, contact_number) values
(1, 'Super Admin', '9800000100'),
(2, 'System Admin', '9800000101');

insert into admins (admin_id, user_id) values
('A-001', 1),
('A-002', 2);

-- receptionists
insert into users (username, password, role) values
('reception1', 'reception123', 'receptionist'),
('reception2', 'reception123', 'receptionist');

insert into user_profiles (user_id, full_name, contact_number) values
(3, 'Ram Receptionist', '9800000003'),
(4, 'Gita Receptionist', '9800000020');

insert into receptionists (receptionist_id, user_id, shift) values
('R-001', 3, 'morning'),
('R-002', 4, 'afternoon');

-- patients
insert into users (username, password, role) values
('patient1', 'patient123', 'patient'),
('patient2', 'patient123', 'patient'),
('patient3', 'patient123', 'patient');

insert into user_profiles (user_id, full_name, dob, age, blood_group, gender, contact_number, address) values
(5, 'Sita Sharma', '1995-04-12', 31, 'A+', 'female', '9800000001', 'Kathmandu'),
(6, 'Hari Thapa', '1988-07-20', 38, 'O-', 'male', '9800000002', 'Lalitpur'),
(7, 'Anil Koirala', '2000-01-15', 26, 'B+', 'male', '9800000004', 'Bhaktapur');

insert into patients (patient_id, user_id) values
('P-001', 5),
('P-002', 6),
('P-003', 7);

-- doctors (covering all departments)
insert into users (username, password, role) values
('doctor_cardiology', 'doctor123', 'doctor'),
('doctor_neurology', 'doctor123', 'doctor'),
('doctor_orthopedics', 'doctor123', 'doctor'),
('doctor_general', 'doctor123', 'doctor'),
('doctor_pediatrics', 'doctor123', 'doctor');

insert into user_profiles (user_id, full_name, contact_number) values
(8, 'Dr. Anil Khatiwada', '9811111111'),
(9, 'Dr. Meera Shrestha', '9811111112'),
(10, 'Dr. Suresh Lama', '9811111113'),
(11, 'Dr. Rupa Joshi', '9811111114'),
(12, 'Dr. Deepak Giri', '9811111115');

insert into doctors (doctor_id, user_id, specialization, department_id, availability) values
('D-101', 8, 'Cardiologist', 1, 'available'),
('D-102', 9, 'Neurologist', 2, 'available'),
('D-103', 10, 'Orthopedic Surgeon', 3, 'available'),
('D-104', 11, 'General Physician', 4, 'available'),
('D-105', 12, 'Pediatrician', 5, 'available');

use hospital_queue_management_db;