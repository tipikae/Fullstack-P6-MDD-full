DROP TABLE IF EXISTS `COMMENTS`, `SUBSCRIPTIONS`, `POSTS`, `TOPICS`, `USERS`;

CREATE TABLE `TOPICS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `SUBSCRIPTIONS` (
  `user_id` INT,
  `topic_id` INT
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `user_id` INT NOT NULL,
  `topic_id` INT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `comment` VARCHAR(255) NOT NULL,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `USERS` ADD UNIQUE (`email`, `username`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS`(`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS`(`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`post_id`) REFERENCES `POSTS`(`id`);

INSERT INTO TOPICS (name, description) VALUES ('JavaScript', 'Lorem ipsum');
INSERT INTO TOPICS (name, description) VALUES ('Java', 'Lorem ipsum');
INSERT INTO TOPICS (name, description) VALUES ('Python', 'Lorem ipsum');
INSERT INTO TOPICS (name, description) VALUES ('Web3', 'Lorem ipsum');
INSERT INTO TOPICS (name, description) VALUES ('Spring', 'Lorem ipsum');
INSERT INTO TOPICS (name, description) VALUES ('Angular', 'Lorem ipsum');
