#USER
INSERT INTO crypto_coffee.user (is_locked, company_id, created_at, id, updated_at, name, password, role, username) VALUES (false, 4, '2024-05-20 22:57:08.910088', 1, '2024-05-20 23:40:34.248869', '홍길동', '$2a$10$0SS/DspgSSQxZeME36YP1.KYmz47XpOqfzD1kAOAiXcEoKcnoBy3a', 'ROLE_ADMIN', 'test@gmail.com');
INSERT INTO crypto_coffee.user (is_locked, company_id, created_at, id, updated_at, name, password, role, username) VALUES (false, 4, '2024-05-22 23:39:31.881314', 2, '2024-05-22 23:39:31.881314', '곽병민', '$2a$10$PfJSZk3xhaBYvjV6dEqiCel0d8dUqzYnrKFwvd13mOZdRekKyoJle', 'ROLE_ADMIN', 'kbm');
INSERT INTO crypto_coffee.user (is_locked, company_id, created_at, id, updated_at, name, password, role, username) VALUES (false, 4, '2024-05-23 00:29:16.284028', 3, '2024-05-23 00:29:16.284028', '아무개', '$2a$10$mXLv6OipdWXWZxVNhF81DemR7ubXAhTYHfn97zIDAUFmgQgjqiFMC', 'ROLE_ADMIN', 'admin0612');

#COMPANY
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:37:00.814189', 1, '2024-05-20 22:37:00.814189', '서울', '커피회사', '123-45-67890', '강남대로', '12345');
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:37:28.292954', 2, '2024-05-20 22:37:28.292954', '서울', '종이회사', '123-45-33333', '강남대로', '242424');
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:37:51.789260', 3, '2024-05-20 22:37:51.789260', '인천', '인천대학교', '123-45-33333', '아카데미로 119', '119119');
INSERT INTO crypto_coffee.company (created_at, id, updated_at, city, name, number, street, zipcode) VALUES ('2024-05-20 22:41:15.787826', 4, '2024-05-20 22:41:15.787826', '인천', 'CryptoCoffee', '123-45-67890', '아카데미로 119', '12345');

#ITEM
INSERT INTO crypto_coffee.item (category_id, created_at, id, updated_at, description, name, price) VALUES (1, '2024-05-25 23:46:28.846987', 7, '2024-05-25 23:46:28.846987', '카페 라떼다', '카페 라떼', 2500);
INSERT INTO crypto_coffee.item (category_id, created_at, id, updated_at, description, name, price) VALUES (2, '2024-05-25 23:47:02.639875', 8, '2024-05-25 23:47:02.639875', '달고 달디단 허니밀크티', '허니 밀크티', 1800);
INSERT INTO crypto_coffee.item (category_id, created_at, id, updated_at, description, name, price) VALUES (3, '2024-05-25 23:47:30.340850', 9, '2024-05-25 23:47:30.340850', '달디 달고 시고 신 허니 유자', '허니 유자', 2200);

#CATEGORY
INSERT INTO crypto_coffee.category (created_at, id, updated_at, name) VALUES ('2024-05-25 23:34:05.990254', 1, '2024-05-25 23:34:05.990254', '커피');
INSERT INTO crypto_coffee.category (created_at, id, updated_at, name) VALUES ('2024-05-25 23:34:13.049885', 2, '2024-05-25 23:34:13.049885', '논커피');
INSERT INTO crypto_coffee.category (created_at, id, updated_at, name) VALUES ('2024-05-25 23:34:26.198815', 3, '2024-05-25 23:34:26.198815', '티/에이드');