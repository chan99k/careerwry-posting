CREATE TABLE IF NOT EXISTS `post`
(
    `title`              varchar(40)  NOT NULL,
    `content`            TEXT         NOT NULL,
    `view_count`         bigint(20)   NOT NULL DEFAULT '0',
    `nickname`           VARCHAR(255),
    `job_position`       VARCHAR(255),
    `picture`            VARCHAR(255),
    `id_provider`        VARCHAR(255) NOT NULL,
    `user_id`             VARCHAR(255) NOT NULL,
    `last_modified_date` datetime(6)           DEFAULT NULL,
    `last_modified_by`   VARCHAR(255)          DEFAULT NULL,
    `created_date`       datetime(6)           DEFAULT NULL,
    `created_by`         VARCHAR(255)          DEFAULT NULL,
    `token`              varchar(20)           DEFAULT NULL,
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`token`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


