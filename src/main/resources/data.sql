INSERT INTO ROLE VALUES ('ROLE_ADMIN');
INSERT INTO ROLE VALUES ('ROLE_CUSTOMER');

INSERT INTO USER_MODEL (login, encrypt_password, authority, moment) VALUES ('admin@gmail.com', '$2a$10$/EztzsS9ni/1zma.SvyldOt/flS3IzdnqhNxYLSXV9C6amlKUtaja', 'ROLE_ADMIN', '2021-04-08');
INSERT INTO USER_MODEL (login, encrypt_password, authority, moment) VALUES ('customer@gmail.com', '$2a$10$d.l2a7ewy114GjvsW.Nruu/e956lMeLfbtSGm6vp8RMUcdR1XVm7K', 'ROLE_CUSTOMER', '2021-04-08');

