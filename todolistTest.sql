-- -----------------------------------------------------
-- Table `todolist-test`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolistTest`.`task` (
  `id` INT NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_time` DATETIME(6) NULL DEFAULT NULL,
  `start_time` DATETIME(6) NULL DEFAULT NULL,
  `status` BIT(1) NOT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
