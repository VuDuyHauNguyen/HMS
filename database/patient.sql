drop table if exists patients;
create table patients (
	id INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	gender CHAR(1), 
    dob DATE,
    phone VARCHAR(20),
    email VARCHAR(255),
    address VARCHAR(255)
);
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Bathsheba', 'Rudge', 'f', '1970-11-24', '498-571-9399', 'brudge0@cafepress.com', '4137 Karstens Circle');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Kathye', 'Shardlow', 'f', '1980-08-29', '949-475-0388', 'kshardlow1@alexa.com', '69 Dovetail Street');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Pancho', 'O''Carrol', 'm', '1960-02-11', '731-644-8160', 'pocarrol2@spiegel.de', '7 Thompson Point');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Bevin', 'Steljes', 'f', '1978-01-29', '692-902-8190', 'bsteljes3@csmonitor.com', '76795 Superior Junction');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Yettie', 'Finn', 'm', '1956-03-04', '725-718-5534', 'yfinn4@seesaa.net', '52974 Vermont Way');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Reta', 'Twoohy', 'f', '1967-06-29', '416-771-4679', 'rtwoohy5@oracle.com', '3696 Farragut Court');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Gilbertine', 'Lineham', 'f', '1999-04-02', '902-145-0942', 'glineham6@soup.io', '0760 Melvin Parkway');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Ennis', 'Wassell', 'f', '1994-02-02', '635-901-3748', 'ewassell7@vimeo.com', '5245 Dahle Place');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Flint', 'Ruusa', 'm', '1945-05-26', '894-361-3968', 'fruusa8@php.net', '599 Farwell Circle');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Corabelle', 'Bodell', 'f', '1977-07-08', '458-739-6458', 'cbodell9@yale.edu', '161 Stone Corner Center');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Kaine', 'Ottam', 'm', '1959-08-27', '984-236-0842', 'kottama@forbes.com', '43535 Fordem Drive');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Miof mela', 'Ruit', 'm', '1985-10-12', '192-837-3571', 'mruitb@va.gov', '08 Wayridge Crossing');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Ignace', 'Lowther', 'm', '1955-03-12', '611-676-9004', 'ilowtherc@kickstarter.com', '42315 Welch Pass');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Pembroke', 'Marklund', 'm', '1973-11-21', '293-756-6412', 'pmarklundd@liveinternet.ru', '85 Corben Trail');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Kylie', 'Thurstance', 'm', '1983-02-24', '860-401-2229', 'kthurstancee@symantec.com', '86 Starling Court');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Zora', 'Testo', 'm', '1980-02-21', '823-138-5023', 'ztestof@nps.gov', '14222 Brickson Park Place');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Clifford', 'Abriani', 'f', '1969-09-14', '476-630-6567', 'cabrianig@mtv.com', '9106 Superior Place');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Noe', 'Phaup', 'f', '1979-09-06', '729-501-1428', 'nphauph@rediff.com', '03652 Donald Court');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Anton', 'Albon', 'm', '2004-09-17', '175-290-5996', 'aalboni@toplist.cz', '93590 Schurz Trail');
insert into patients (firstName, lastName, gender, dob, phone, email, address) values ('Jacintha', 'Polding', 'm', '1985-06-01', '161-284-0120', 'jpoldingj@e-recht24.de', '3 Forest Terrace');