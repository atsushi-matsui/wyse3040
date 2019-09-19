-- user
INSERT INTO user (user_id, password, first_name, last_name, role_name, status) VALUES ('001', 'test', 'atsushi', 'matsui', 'admin', 'ENTRY');

-- wyse
INSERT INTO wyse (wyse_id, management_nubmer, status, reservation_date, return_date) VALUES ('001','0000000001','USING','20190901000000','20191001000000');
INSERT INTO wyse (wyse_id, management_nubmer, status, reservation_date, return_date) VALUES ('002','0000000002','ACTIVATED','20190901000000','20191001000000');
INSERT INTO wyse (wyse_id, management_nubmer, status, reservation_date, return_date) VALUES ('003','0000000003','ACTIVATED','20190901000000','20191001000000');