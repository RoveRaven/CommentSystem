DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS users;
CREATE TABLE  users (id INT PRIMARY KEY, 
		      name VARCHAR(64) NOT NULL UNIQUE, 
		      role VARCHAR(5), 
		      registration_date DATE,
		      avatar BYTEA);
CREATE TABLE comments (comment_id INT PRIMARY KEY, 
			parent_id INT, 
			user_id INT REFERENCES users(id), 
			comment_time TIMESTAMPTZ,
			text TEXT);
