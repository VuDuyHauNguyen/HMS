drop table if exists appointments;
create table appointments (
	id INT AUTO_INCREMENT PRIMARY KEY,
	patientId INT,
	receptionistId INT,
	appointmentTime DATETIME,
	status VARCHAR(6),
	constraint FK_PATIENT_APPOINTMENT foreign key (patientId) references patients (id),
	constraint FK_RECEPTIONIST_APPOINTMENT foreign key (receptionistId) references employees (id)
);
insert into appointments (patientId, receptionistId, appointmentTime, status) values (18, 7, '2019-02-17 12:00:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (5, 13, '2019-07-20 10:15:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (20, 10, '2019-07-18 15:30:00', 'book');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 12, '2018-08-11 13:30:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 8, '2019-05-26 14:15:00', 'book');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 18, '2018-08-14 9:15:00', 'book');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (10, 2, '2019-03-07 10:45:00', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (13, 16, '2018-07-19 15:45:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (9, 7, '2019-01-22 13:30:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (9, 13, '2019-06-17 13:15:00', 'book');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (8, 4, '2018-08-08 12:30:00', 'book');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (15, 10, '2019-07-27 11:15:00', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (10, 16, '2019-05-11 14:30:00', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (9, 8, '2019-04-24 11:00:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (7, 2, '2018-12-23 12:15:00', 'book');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (15, 1, '2019-07-29 15:45:00', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (7, 15, '2018-08-19 10:00:00', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 12, '2019-03-20 12:45:00', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (2, 1, '2019-03-19 13:45:00', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (16, 11, '2019-07-03 10:15:00', 'cancel');