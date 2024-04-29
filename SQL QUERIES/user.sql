CREATE DATABASE IF NOT EXISTS market;
use market;

drop table IF EXISTS auth_questions;
CREATE TABLE auth_questions(
id int PRIMARY KEY,
question text
);

drop table IF EXISTS user;
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    first_name text NOT NULL, 
    last_name text NOT NULL,
	email VARCHAR(500) ,
    password VARCHAR(255) NOT NULL, -- Assuming hashed passwords stored
    auth_question_answer text NOT NULL,
    auth_question_id int NOT NULL references auth_questions(id) ,
    Role TEXT,
    UNIQUE(email)
);

drop table IF EXISTS products;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    product_description TEXT,
    product_price INT,
    porduct_image TEXT  
);
drop table IF EXISTS orders;
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(product_id),
    user_id INT REFERENCES users(user_id),
    order_status VARCHAR(20),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price int,
    quantity int
);
drop table IF EXISTS cart;
CREATE TABLE cart (
    cart_id SERIAL PRIMARY KEY,	
    product_id INT REFERENCES products(id),
    user_id INT REFERENCES users(id),
    product_image Text,
    product_Name Text,
    price INT,
    quantity INT
);



-- DROP TABLE IF EXISTS OTP;
-- CREATE TABLE OTP(
--     id SERIAL AUTO_INCREMENT PRIMARY KEY,
-- 	user_email Text REFERENCES users(email),
--     date text,
--     otp int(6),
-- 	CONSTRAINT unique_user_id UNIQUE (user_email)
-- );


insert into user values(1,"DepoGramming","Ali","Eldeep","alieldeep111@gmail.com","$2a$10$iYmrSlujOb7uqfqYdRsBJu9vtkN0Wz4znrK8dPXzP/aD3CHcXy.OK","$2a$10$H31L5gXJT9h8o7ik9pWk5e90m2L.k2c9g1gNtaU3hxp.UzE0t2NQu","1","USER");
insert into products values("1", "t-shirt", "a stylish t-shirt", "400", "www.http/meow");
insert into products values(45, "trousers", "a stylish trousers", 200, "www.http/mememeow");
insert into products values(4,"meow","not meow",500,"helpppp");



INSERT INTO orders (product_id, user_id, order_status, price, quantity)
VALUES (1, 1, 'on the way', 30, 40);

INSERT INTO auth_questions (id, question) VALUES
(1, 'What is your mother''s  name?'),
(2, 'What city were you born in?'),
(3, 'What is the name of your first pet?'),
(4, 'What is your favorite food?'),
(5, 'In what year did you graduate from high school?'),
(6, 'What is the name of your favorite teacher?'),
(7, 'What is the model of your first car?'),
(8, 'What is your favorite book?'),
(9, 'What is your favorite movie?'),
(10, 'What is your favorite color?');

INSERT INTO user (id, user_name, first_name, last_name, email, password, auth_question_answer, auth_question_id, role)
VALUES ('2', 'safwat', 'safwat', 'safwat', 'safwat2@gmail.com', '$2a$10$iYmrSlujOb7uqfqYdRsBJu9vtkN0Wz4znrK8dPXzP/aD3CHcXy.OK', '$2a$10$H31L5gXJT9h8o7ik9pWk5e90m2L.k2c9g1gNtaU3hxp.UzE0t2NQu', '1', 'ADMIN');

