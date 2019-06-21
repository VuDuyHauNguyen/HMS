create table patients (
	patientId INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	gender CHAR(1), 
    dob DATE,
    phone VARCHAR(20),
    email VARCHAR(255) UNIQUE,
    address VARCHAR(255)
);

insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (1, 'Bathsheba', 'Rudge', 'f', '0598-11-24', '498-571-9399', 'brudge0@cafepress.com', '4137 Karstens Circle');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (2, 'Kathye', 'Shardlow', 'f', '0781-08-29', '949-475-0388', 'kshardlow1@alexa.com', '69 Dovetail Street');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (3, 'Pancho', 'O''Carrol', 'm', '1025-02-11', '731-644-8160', 'pocarrol2@spiegel.de', '7 Thompson Point');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (4, 'Bevin', 'Steljes', 'f', '1594-01-29', '692-902-8190', 'bsteljes3@csmonitor.com', '76795 Superior Junction');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (5, 'Yettie', 'Finn', 'm', '0816-03-04', '725-718-5534', 'yfinn4@seesaa.net', '52974 Vermont Way');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (6, 'Reta', 'Twoohy', 'f', '1397-06-29', '416-771-4679', 'rtwoohy5@oracle.com', '3696 Farragut Court');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (7, 'Gilbertine', 'Lineham', 'f', '1907-04-02', '902-145-0942', 'glineham6@soup.io', '0760 Melvin Parkway');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (8, 'Ennis', 'Wassell', 'f', '1334-02-02', '635-901-3748', 'ewassell7@vimeo.com', '5245 Dahle Place');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (9, 'Flint', 'Ruusa', 'm', '1845-05-26', '894-361-3968', 'fruusa8@php.net', '599 Farwell Circle');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (10, 'Corabelle', 'Bodell', 'f', '0867-07-08', '458-739-6458', 'cbodell9@yale.edu', '161 Stone Corner Center');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (11, 'Kaine', 'Ottam', 'm', '0907-08-27', '984-236-0842', 'kottama@forbes.com', '43535 Fordem Drive');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (12, 'Miof mela', 'Ruit', 'm', '0985-10-12', '192-837-3571', 'mruitb@va.gov', '08 Wayridge Crossing');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (13, 'Ignace', 'Lowther', 'm', '0655-03-12', '611-676-9004', 'ilowtherc@kickstarter.com', '42315 Welch Pass');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (14, 'Pembroke', 'Marklund', 'm', '1013-11-21', '293-756-6412', 'pmarklundd@liveinternet.ru', '85 Corben Trail');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (15, 'Kylie', 'Thurstance', 'm', '0873-02-24', '860-401-2229', 'kthurstancee@symantec.com', '86 Starling Court');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (16, 'Zora', 'Testo', 'm', '0930-02-21', '823-138-5023', 'ztestof@nps.gov', '14222 Brickson Park Place');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (17, 'Clifford', 'Abriani', 'f', '1160-09-14', '476-630-6567', 'cabrianig@mtv.com', '9106 Superior Place');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (18, 'Noe', 'Phaup', 'f', '1878-09-06', '729-501-1428', 'nphauph@rediff.com', '03652 Donald Court');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (19, 'Anton', 'Albon', 'm', '1514-09-17', '175-290-5996', 'aalboni@toplist.cz', '93590 Schurz Trail');
insert into patients (patientId, firstName, lastName, gender, dob, phone, email, address) values (20, 'Jacintha', 'Polding', 'm', '0298-06-01', '161-284-0120', 'jpoldingj@e-recht24.de', '3 Forest Terrace');