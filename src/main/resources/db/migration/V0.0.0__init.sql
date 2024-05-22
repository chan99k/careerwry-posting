CREATE TABLE IF NOT EXISTS post
(
    title              VARCHAR(40)         NOT NULL,
    content            TEXT                NOT NULL,
    nickname           VARCHAR(255),
    position_job        VARCHAR(255),
    profile_image       VARCHAR(255),
    last_modified_date datetime(6)         null,
    last_modified_by   VARCHAR(255)        null,
    created_date       datetime(6)         null,
    created_by         VARCHAR(255)        null,
    token              VARCHAR(255) UNIQUE NOT NULL,
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY
);

