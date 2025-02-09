CREATE TABLE file
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NULL,
    last_modified_date datetime              NULL,
    created_by         VARCHAR(255)          NULL,
    last_modified_by   VARCHAR(255)          NULL,
    s3url              VARCHAR(255)          NOT NULL,
    content_type       VARCHAR(255)          NOT NULL,
    file_size          BIGINT                NOT NULL,
    post_id            BIGINT                NOT NULL,
    token              VARCHAR(255)          NULL,
    CONSTRAINT pk_file PRIMARY KEY (id)
);

ALTER TABLE file
    ADD CONSTRAINT uc_file_token UNIQUE (token);

ALTER TABLE file
    ADD CONSTRAINT FK_FILE_ON_POST FOREIGN KEY (post_id) REFERENCES post (id);