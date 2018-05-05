INSERT INTO employee (firstname, lastname) VALUES ('Michal', 'Sterba');
INSERT INTO employee (firstname, lastname) VALUES ('Radek', 'Zika');
INSERT INTO employee (firstname, lastname) VALUES ('Marek', 'Balger');
INSERT INTO employee (firstname, lastname) VALUES ('Jakub', 'Cajka');
INSERT INTO employee (firstname, lastname) VALUES ('Marek', 'Kneys');
INSERT INTO employee (firstname, lastname) VALUES ('Tomas', 'Jezowicz');
INSERT INTO employee (firstname, lastname) VALUES ('Josef', 'Valcuha');
INSERT into employee (id, firstname, lastname) values (8, 'Wanda', 'Maximoff');
insert into secure.role(id, comment, name) values (1, 'Can do anything', 'ROLE_ADMIN');
insert into secure.employee_metadata(id, email, password, username, employee_id)
values (1, 'scarletWitch@avengers.com', '$2a$10$QVcRG.yL54ht.VdAbV0lI.BEJayCXAu5clF8jmDZ4m38RIuISJt0W','wanda', 8);
insert into secure.employee_role(id, employee_id, role_id) VALUES (1, 8, 1);
insert into player_group values (1, 'ITG');
insert into employee_player_group values (8, 1);