CREATE TABLE `project_version`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'auto increment pk',
    `name` VARCHAR(255) NOT NULL COMMENT '버전 명',
    CONSTRAINT `PK_PROJECT_VERSION` PRIMARY KEY (`id`)
);

CREATE TABLE `project`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'auto increment pk',
    `name`               VARCHAR(255) NOT NULL COMMENT '프로젝트 명',
    `project_version_id` BIGINT       NOT NULL COMMENT 'project version fk',
    `start_date`         DATE         NOT NULL COMMENT '프로젝트 시작일',
    `created_at`         DATETIME     NOT NULL COMMENT 'row 생성일',
    `modified_at`        DATETIME     NOT NULL COMMENT 'row 수정일',
    CONSTRAINT `PK_PROJECT` PRIMARY KEY (`id`),
    CONSTRAINT `FK_project_version_TO_project` FOREIGN KEY (`project_version_id`) REFERENCES `project_version` (`id`)
);

CREATE TABLE `project_branch`
(
    `id`              BIGINT   NOT NULL AUTO_INCREMENT COMMENT 'auto increment pk',
    `project_id`      BIGINT   NOT NULL COMMENT 'project fk',
    `branch_code`     INTEGER  NOT NULL COMMENT '분기 번호',
    `branch_quantity` INTEGER  NOT NULL COMMENT '분기 생산 수량',
    `created_at`      DATETIME NOT NULL COMMENT 'row 생성일',
    `modified_at`     DATETIME NOT NULL COMMENT 'row 수정일',
    CONSTRAINT `PK_PROJECT_BRANCH` PRIMARY KEY (`id`),
    CONSTRAINT `FK_project_TO_project_branch` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
);

CREATE TABLE `project_branch_bom`
(
    `id`                  BIGINT                                       NOT NULL AUTO_INCREMENT COMMENT 'auto increment pk',
    `project_branch_id`   BIGINT                                       NOT NULL COMMENT 'project branch fk',
    `item_type`           ENUM ('PROCESSED', 'PURCHASED', 'INJECTION') NOT NULL COMMENT '품목 구분',
    `drawing_code`        VARCHAR(255)                                 NOT NULL COMMENT '품목 도번',
    `item_name`           VARCHAR(255)                                 NOT NULL COMMENT '품명',
    `specification`       TEXT                                         NOT NULL COMMENT '품목 규격',
    `quantity_per_unit`   INTEGER                                      NOT NULL COMMENT '한 생산 품목 별 단위 수량',
    `unit`                ENUM ('EA', 'BOX', 'ROLL')                   NOT NULL COMMENT '수량 단위',
    `is_consign_material` BOOLEAN                                      NOT NULL COMMENT '사급자재 유무',
    `created_at`          DATETIME                                     NOT NULL COMMENT 'row 생성일',
    `modified_at`         DATETIME                                     NOT NULL COMMENT 'row 수정일',
    CONSTRAINT `PK_PROJECT_BRANCH_BOM` PRIMARY KEY (`id`),
    CONSTRAINT `FK_project_branch_TO_project_branch_bom` FOREIGN KEY (`project_branch_id`) REFERENCES `project_branch` (`id`)
);
