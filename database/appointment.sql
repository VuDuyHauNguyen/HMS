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
insert into appointments (patientId, receptionistId, appointmentTime, status) values (18, 7, '2019-02-17 23:00:53', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (5, 13, '2019-07-20 23:17:54', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (20, 10, '2019-07-18 15:42:26', 'open');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 12, '2018-08-11 06:30:34', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 8, '2019-05-26 16:15:34', 'open');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 18, '2018-08-14 07:18:53', 'open');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (10, 2, '2019-03-07 05:46:12', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (13, 16, '2018-07-19 05:44:53', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (9, 7, '2019-01-22 03:36:55', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (9, 13, '2019-06-17 03:20:37', 'open');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (8, 4, '2018-08-08 21:33:16', 'open');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (15, 10, '2019-07-27 01:13:11', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (10, 16, '2019-05-11 04:27:30', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (9, 8, '2019-04-24 11:57:27', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (7, 2, '2018-12-23 19:12:23', 'open');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (15, 1, '2019-07-29 15:11:10', 'cancel');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (7, 15, '2018-08-19 12:56:47', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (12, 12, '2019-03-20 22:51:59', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (2, 1, '2019-03-19 07:43:14', 'done');
insert into appointments (patientId, receptionistId, appointmentTime, status) values (16, 11, '2019-07-03 00:16:49', 'cancel');