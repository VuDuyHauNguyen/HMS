drop table if exists checkUpRecords;
create table checkUpRecords (
	id PRIMARY KEY,
	patientId INT,
	doctorId INT,
	checkUpRecordTime DATETIME,
	medicalProblem TEXT,
	checkupResult TEXT,
	prescriptions TEXT,
	status VARCHAR(10),
	constraint FK_PATIENT_RECORD foreign key (patientId) references patients (id),
	constraint FK_DOCTOR_RECORD foreign key (doctorId) references employees (id)
)