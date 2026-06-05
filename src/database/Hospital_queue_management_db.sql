drop database hospital_queue_management_db;

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
    created_at timestamp default current_timestamp
);

-- 3. patients
create table patients (
    patient_id varchar(10) primary key,
    user_id int not null,
    full_name varchar(100) not null,
    dob date null,
    age int not null,
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

-- patients
alter table patients
add constraint fk_patients_to_users
foreign key (user_id) references users(user_id);

-- doctors
alter table doctors
add constraint fk_doctors_to_users
foreign key (user_id) references users(user_id);

alter table doctors
add constraint fk_doctors_to_departments
foreign key (department_id) references departments(department_id);

-- security_questions
alter table security_questions
add constraint fk_security_to_users
foreign key (user_id) references users(user_id);

-- appointments
alter table appointments
add constraint fk_appointments_to_patients
foreign key (patient_id) references patients(patient_id);

alter table appointments
add constraint fk_appointments_to_doctors
foreign key (doctor_id) references doctors(doctor_id);

-- queue
alter table queue
add constraint fk_queue_to_appointments
foreign key (appointment_id) references appointments(appointment_id);

alter table queue
add constraint fk_queue_to_patients
foreign key (patient_id) references patients(patient_id);

alter table queue
add constraint fk_queue_to_doctors
foreign key (doctor_id) references doctors(doctor_id);

-- waitlist
alter table waitlist
add constraint fk_waitlist_to_patients
foreign key (patient_id) references patients(patient_id);

alter table waitlist
add constraint fk_waitlist_to_doctors
foreign key (doctor_id) references doctors(doctor_id);

-- medical_records
alter table medical_records
add constraint fk_records_to_appointments
foreign key (appointment_id) references appointments(appointment_id);

alter table medical_records
add constraint fk_records_to_patients
foreign key (patient_id) references patients(patient_id);

alter table medical_records
add constraint fk_records_to_doctors
foreign key (doctor_id) references doctors(doctor_id);

-- ratings
alter table ratings
add constraint fk_ratings_to_appointments
foreign key (appointment_id) references appointments(appointment_id);

alter table ratings
add constraint fk_ratings_to_patients
foreign key (patient_id) references patients(patient_id);

alter table ratings
add constraint fk_ratings_to_doctors
foreign key (doctor_id) references doctors(doctor_id);

-- departments
insert into departments (department_name, description) values
('cardiology', 'heart and vascular diseases'),
('neurology', 'brain and nervous system'),
('orthopedics', 'bones and joints'),
('general medicine', 'general health checkup'),
('pediatrics', 'children health');

-- admin user
insert into users (username, password, role) values
('admin', 'admin123', 'admin');

show tables;
describe patients;
select * from patients;
select * from users;
select * from security_questions;