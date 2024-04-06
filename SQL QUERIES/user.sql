create database market;
use market;
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL,
    firstName text NOT NULL, 
    lastName text NOT NULL,
    email text NOT NULL,
    password VARCHAR(255) NOT NULL, -- Assuming hashed passwords stored
    token VARCHAR(255) NOT NULL,
    UNIQUE(email),
    UNIQUE (token)
);
insert into user values("1","ali","ali","eldeep","alieldeep111@gmail.com","123456","aaaaaa");

select * from user;