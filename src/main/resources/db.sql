CREATE TABLE todo_item
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    completed   BOOLEAN DEFAULT FALSE,
    target_date DATE
);