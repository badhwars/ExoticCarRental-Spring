insert into roles (rolename) values ('ROLE_ADMIN');
insert into roles (rolename) values ('ROLE_USER');


insert into customer(address, enabled, encrypted_password, f_name, l_name, phone_number, username) values ('271 Richvale Drive South', 1, '$2a$10$0wqM95wfPA2tPhRYmOkLl.eJ57ZyyTke7vR/gzO0PbuLyaNNmArIW', 'Administrator', 'Admin', 6475287012, 'admin');
insert into customer(address, enabled, encrypted_password, f_name, l_name, phone_number, username) values ('271 Richvale Drive South', 1, '$2a$10$ocfnB8Kl1u3AsBi2T9lWUeptR/PcZwl91JHA3N/fXH9ya0pUqlbby', 'Sahashra', 'Rajbhandari', 6475287012, 'sraj');
insert into customer(address, enabled, encrypted_password, f_name, l_name, phone_number, username) values ('44 Mclaughlin Road', 1, '$2a$10$9VAjKzkU7/Xc9scwv2e3J.af9kMWHx7dyyG/JKSpVDOC3oEX26qHe', 'Sarvesh', 'Badhwar', 6478340376, 'badhwars');
insert into customer(address, enabled, encrypted_password, f_name, l_name, phone_number, username) values ('75 Chingaucousy Road', 1, '$2a$10$9VAjKzkU7/Xc9scwv2e3J.af9kMWHx7dyyG/JKSpVDOC3oEX26qHe', 'Harnoor', 'Singh', 4377792450, 'harnoors');

insert into customer_roles(customer_id,roles_id) values (1,1);
insert into customer_roles(customer_id,roles_id) values (2,2);

insert into car(daily_charge, make, model, year, image) values (1700.00, 'Ferrari', '458 Spyder', 2009, '458Spyder.jpeg');
insert into car(daily_charge, make, model, year, image) values (1950.00, 'Lamborghini', 'Huracan Performante', 2014, 'performante.png');
insert into car(daily_charge, make, model, year, image) values (3000.00, 'Bugatti', 'Chiron', 2018, 'Chiron.png');
insert into car(daily_charge, make, model, year, image) values (1300.00, 'Mercedes', 'AMG GTR', 2014, 'amgGtr.png');
insert into car(daily_charge, make, model, year, image) values (2000.00, 'Lamborghini', 'Aventador S Roadster', 2012, 'sRoadster.png');
insert into car(daily_charge, make, model, year, image) values (2200.00, 'Lamborghini', 'Aventador SV Roadster', 2019, 'svRoadster.png');
insert into car(daily_charge, make, model, year, image) values (1850.00, 'Lamborghini', 'Huracan Evo Spyder', 2020, 'EvoSpyder.png');
insert into car(daily_charge, make, model, year, image) values (2900.00, 'Lamborghini', 'Centenario', 2016, 'centenario.png');
insert into car(daily_charge, make, model, year, image) values (3200.00, 'Ferrari', 'LaFerrari', 2015, 'laferrari.jpeg');
insert into car(daily_charge, make, model, year, image) values (1950.00, 'Ferrari', 'F12 TRS', 2014, 'f12.png');
insert into car(daily_charge, make, model, year, image) values (1700.00, 'BMW', 'i8', 2015, 'i8.png');
insert into car(daily_charge, make, model, year, image) values (1750.00, 'Audi', 'R8', 2011, 'R8.png');
insert into car(daily_charge, make, model, year, image) values (1650.00, 'Porsche', '911 GT3 RS4', 2014, '911GT3.png');
insert into car(daily_charge, make, model, year, image) values (1450.00, 'Tesla', 'S Model', 2017, 'ModelS.png');
insert into car(daily_charge, make, model, year, image) values (1600.00, 'Tesla', 'ModelX', 2019, 'ModelX.png');
insert into car(daily_charge, make, model, year, image) values (1400.00, 'Tesla', 'Model3', 2020, 'Model3.png');
insert into car(daily_charge, make, model, year, image) values (2000.00, 'Porsche', 'Cayman GT4', 2016, 'CaymanGT4.png');