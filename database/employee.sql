
/*
 * gender: f; m
 * role: 1 = admin; 2 = receptionist; 3 = doctor; 4 = technologist
 * status: -1 = disable, 1 = active
 */
create table employees (
	id INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	gender CHAR(1), 
    dob DATE,
    phone VARCHAR(20),
    email VARCHAR(255) UNIQUE,
    address VARCHAR(255),
	role TINYINT, 
	status TINYINT,
    password VARCHAR(50)
);

/*INSERT TEST DATA*/
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Doctor', 'Boucher', 'f', '1977-04-12', '208-982-2375', 'doctor@hospital.com', '245 Brickson Park Lane', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Admin', 'Hospital', 'm', '1961-05-19', '750-970-9083', 'admin@hospital.com', '10023 Kim Road', 1, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Technologist', 'Gilchriest', 'f', '1987-01-30', '106-804-7722', 'technologist@hospital.com', '34 Farwell Avenue', 4, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Loise', 'Slaymaker', 'm', '1961-03-05', '488-427-2113', 'lslaymaker3@ihg.com', '203 Blue Bill Park Junction', 4, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Tamra', 'Waiting', 'f', '2005-02-21', '849-760-0355', 'twaiting4@google.it', '8 Bellgrove Trail', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Oralia', 'Ducker', 'f', '2004-08-20', '557-935-6148', 'oducker5@mtv.com', '269 Gulseth Hill', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Receptionist', 'Muccino', 'm', '1958-09-19', '684-669-3913', 'receptionist@hospital.com', '392 Boyd Court', 2, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Honoria', 'Suddards', 'f', '1999-03-27', '275-338-6682', 'hsuddards7@reverbnation.com', '239 Iowa Parkway', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Gail', 'Rumsby', 'f', '2000-05-04', '527-922-0188', 'grumsby8@squidoo.com', '58400 Raven Center', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Giavani', 'Boustred', 'm', '1962-01-31', '396-370-5566', 'gboustred9@taobao.com', '4844 Eliot Terrace', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Carie', 'Hundy', 'm', '1964-04-14', '755-309-4441', 'chundya@wordpress.com', '115 West Court', 4, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Pancho', 'Rafter', 'm', '1995-06-22', '213-710-5074', 'prafterb@spotify.com', '5 Monica Lane', 2, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Rebecca', 'Ambrosoli', 'm', '1993-04-03', '340-591-3849', 'rambrosolic@shop-pro.jp', '427 Packers Parkway', 2, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Leona', 'Crilley', 'f', '1996-07-20', '664-782-7188', 'lcrilleyd@dyndns.org', '99728 Moland Crossing', 4, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Arch', 'Braganza', 'm', '1973-05-06', '278-680-0777', 'abraganzae@xrea.com', '4 Jay Plaza', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Maitilde', 'Mussolini', 'm', '1989-07-31', '205-609-7032', 'mmussolinif@oakley.com', '96 Meadow Ridge Terrace', 4, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Leone', 'Martyn', 'm', '1952-04-21', '178-346-1654', 'lmartyng@upenn.edu', '8926 Meadow Vale Alley', 2, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Doralia', 'Hulke', 'm', '1978-03-11', '355-985-3382', 'dhulkeh@jigsy.com', '1002 Green Ridge Road', 2, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Isahella', 'Beamiss', 'm', '1983-06-30', '918-912-7328', 'ibeamissi@loc.gov', '5196 Superior Point', 3, 1, 'hospital');
insert into employees (firstName, lastName, gender, dob, phone, email, address, role, status, password) values ('Gussy', 'Skinley', 'f', '1955-07-02', '214-900-5547', 'gskinleyj@taobao.com', '93 Sachtjen Avenue', 4, 1, 'hospital');