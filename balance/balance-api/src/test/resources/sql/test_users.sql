set foreign_key_checks = 0;
truncate table ACCOUNT;

insert into ACCOUNT values (1, 'test@gmail.com', 'test', 'Test User', '09 1111 2222', '2023-03-15', 1, 1);
insert into ACCOUNT values (2, 'other@gmail.com', 'other', 'Other User', '09 1111 2223', '2023-03-15', 1, 1);

update ACCOUNT_SEQ set next_val = 0 where sequence_name = 'ACCOUNT';

set foreign_key_checks = 1;
