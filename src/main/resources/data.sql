#USER
INSERT INTO crypto_coffee.user (is_locked, company_id, created_at, id, updated_at, name, password, role, username) VALUES (false, 4, '2024-05-20 22:57:08.910088', 1, '2024-05-20 23:40:34.248869', '홍길동', '$2a$10$0SS/DspgSSQxZeME36YP1.KYmz47XpOqfzD1kAOAiXcEoKcnoBy3a', 'ROLE_ADMIN', 'test@gmail.com');
INSERT INTO crypto_coffee.user (is_locked, company_id, created_at, id, updated_at, name, password, role, username) VALUES (false, 4, '2024-05-22 23:39:31.881314', 2, '2024-05-22 23:39:31.881314', '곽병민', '$2a$10$PfJSZk3xhaBYvjV6dEqiCel0d8dUqzYnrKFwvd13mOZdRekKyoJle', 'ROLE_ADMIN', 'kbm');
INSERT INTO crypto_coffee.user (is_locked, company_id, created_at, id, updated_at, name, password, role, username) VALUES (false, 4, '2024-05-23 00:29:16.284028', 3, '2024-05-23 00:29:16.284028', '아무개', '$2a$10$mXLv6OipdWXWZxVNhF81DemR7ubXAhTYHfn97zIDAUFmgQgjqiFMC', 'ROLE_ADMIN', 'admin0612');

#COMPANY
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:37:00.814189', 1, '2024-05-20 22:37:00.814189', '서울', '커피회사', '123-45-67890', '강남대로', '12345');
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:37:28.292954', 2, '2024-05-20 22:37:28.292954', '서울', '종이회사', '123-45-33333', '강남대로', '242424');
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:37:51.789260', 3, '2024-05-20 22:37:51.789260', '인천', '인천대학교', '123-45-33333', '아카데미로 119', '119119');
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:41:15.787826', 4, '2024-05-20 22:41:15.787826', '인천', 'CryptoCoffee', '123-45-67890', '아카데미로 119', '12345');

#