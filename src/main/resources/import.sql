insert into player(player_id, fname, lname, age, username) values (1,'Sebastian', 'Vettel', 34, 'am5');
insert into player(player_id, fname, lname, age, username) values (2,'Fernando', 'Alonso', 40, 'alpine3');
insert into player(player_id, fname, lname, age, username) values (3,'George Russel', 'Bark', 23, 'williams63');
insert into player(player_id, fname, lname, age, username) values (4,'Kimi', 'Raikkonen', 41, 'aromeo7');

insert into account(account_id, saldo, player_id) values (1, 5632, 1);
insert into account(account_id, saldo, player_id) values (2, 9999, 2);
insert into account(account_id, saldo, player_id) values (3, 200, 3);
insert into account(account_id, saldo, player_id) values (4, 15000, 4);


insert into transaction (transaction_id, amount, transaction_type, account_id) values (1, 200, 'DEBIT', 1);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (2, 200, 'DEBIT', 1);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (3, 200, 'CREDIT', 1);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (4, 200, 'DEBIT', 1);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (5, 200, 'CREDIT', 1);

insert into transaction (transaction_id, amount, transaction_type, account_id) values (6, 200, 'DEBIT', 2);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (7, 200, 'DEBIT', 2);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (8, 200, 'CREDIT', 2);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (9, 200, 'DEBIT', 2);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (10, 200, 'CREDIT', 2);

insert into transaction (transaction_id, amount, transaction_type, account_id) values (11, 200, 'DEBIT', 3);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (12, 200, 'DEBIT', 3);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (13, 200, 'CREDIT', 3);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (14, 200, 'DEBIT', 3);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (15, 200, 'CREDIT', 3);

insert into transaction (transaction_id, amount, transaction_type, account_id) values (16, 200, 'DEBIT', 4);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (17, 200, 'DEBIT', 4);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (18, 200, 'CREDIT', 4);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (19, 200, 'DEBIT', 4);
insert into transaction (transaction_id, amount, transaction_type, account_id) values (20, 200, 'CREDIT', 4);

