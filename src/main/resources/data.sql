# INSERT INTO company (name, number, city, street, zipcode)
# VALUES ('회사 이름', '회사 번호', '도시명', '거리명', '우편번호');
INSERT INTO company (name, number, city, street, zipcode)
VALUES ('CryptoCoffee', '051-123-4567', '인천광역시', '아카데미로 119', '22012');
UPDATE company
SET created_at = NOW(), updated_at = NOW()
WHERE created_at IS NULL OR updated_at IS NULL;

# ADMIN ACCOUNT
# 3,admin003,ROLE_ADMIN,$2a$10$gFOCMF61FUlPOX0NU5ZhzuDDZWM4wtNPrZrK3VWOKXhNB0lxiK/8m,admin003,1,2024-04-17 23:28:19.404927,2024-04-17 23:28:19.404927
# 2,admin002,ROLE_ADMIN,$2a$10$ZaqfCV2UePD1am4me.wvgOcTQCXCq3vaKQmWMaQtLNvK.T.NxDe3e,admin002,1,2024-04-17 23:28:12.724345,2024-04-17 23:28:12.724345
# 1,admin001,ROLE_ADMIN,$2a$10$AwAYACjk1m/6UpvjY0ewiuaJirJgwD.1l6oX6DOD.ObJWV83q4sfi,admin001,1,2024-04-17 23:27:58.272988,2024-04-17 23:27:58.272988