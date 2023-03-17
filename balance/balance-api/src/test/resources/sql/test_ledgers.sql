set foreign_key_checks = 0;

truncate table LEDGER;

insert into LEDGER values (1, 'Ledger 1 of 1', 0, 1);
insert into LEDGER values (2, 'Ledger 2 of 1', 1, 1);


insert into LEDGER values (3, 'Ledger 1 of 2', 0, 2);
insert into LEDGER values (4, 'Ledger 2 of 2', 1, 2);
insert into LEDGER values (5, 'Ledger 3 of 2', 1, 2);
insert into LEDGER values (6, 'Ledger 4 of 2', 1, 2);

update LEDGER_SEQ set next_val = 6 where sequence_name = 'LEDGER';

set foreign_key_checks = 1;
