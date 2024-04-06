CREATE DATABASE IF NOT EXISTS market;
use market;
drop table IF EXISTS user;
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    first_name text NOT NULL, 
    last_name text NOT NULL,
	email varchar(400) ,
    password VARCHAR(255) NOT NULL, -- Assuming hashed passwords stored
    token VARCHAR(255) NOT NULL,
    UNIQUE(email),
    UNIQUE (token)
);
insert into user values("1","ali","ali","eldeep","alieldeep111@gmail.com","123456","aaaaaa");

select * from user;

