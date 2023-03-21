set foreign_key_checks = 0;
truncate table ACCOUNT;

insert into ACCOUNT values (1, 'test@gmail.com', 'test', 'Test User', 'pass', '09 1111 2222', '2023-03-15', 1, 1);
insert into ACCOUNT values (2, 'other@gmail.com', 'other', 'Other User', 'pass', '09 1111 2223', '2023-03-15', 1, 1);
insert into ACCOUNT values (3, 'admin@gmail.com', 'admin', 'Admin User', 'pass', '09 1111 2224', '2023-03-15', 0, 1);

insert into ACCOUNT values (4, 'test1@gmail.com', 'test1', 'Test1 User', 'pass', '09 1111 3331', '2023-03-15', 1, 0);
insert into ACCOUNT values (5, 'test2@gmail.com', 'test2', 'Test2 User', 'pass', '09 1111 3332', '2023-03-16', 1, 1);
insert into ACCOUNT values (6, 'test3@gmail.com', 'test3', 'Test3 User', 'pass', '09 1111 3333', '2023-03-17', 1, 2);

update ACCOUNT_SEQ set next_val = 6 where sequence_name = 'ACCOUNT';

set foreign_key_checks = 1;
