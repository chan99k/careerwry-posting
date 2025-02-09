CREATE TABLE IF NOT EXISTS `like`
(
    `id`                 BIGINT              NOT NULL AUTO_INCREMENT,
    `token`              VARCHAR(255) UNIQUE NOT NULL,
    `post_id`            BIGINT              NOT NULL,
    `created_by`         varchar(40) DEFAULT NULL,
    `created_date`       datetime(6) DEFAULT NULL,
    `last_modified_by`   varchar(40) DEFAULT NULL,
    `last_modified_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`post_id`, `created_by`),
    CONSTRAINT `fk_like_post`
        FOREIGN KEY (`post_id`)
            REFERENCES `post` (`id`)
            ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
