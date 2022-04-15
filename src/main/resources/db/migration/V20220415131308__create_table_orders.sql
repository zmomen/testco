CREATE TABLE orders (
id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
amount DOUBLE,
description VARCHAR(200),
created_date DATE,
PRIMARY KEY (id)
)