CREATE TABLE IF NOT EXISTS post
(
    title   VARCHAR(255)        NOT NULL,
    content TEXT                NOT NULL,
    last_modified_date datetime(6)  null,
    created_at          datetime(6)  null,
    token   VARCHAR(255) UNIQUE NOT NULL,
    id      BIGINT AUTO_INCREMENT PRIMARY KEY
);