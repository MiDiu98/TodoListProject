-- -----------------------------------------------------
-- Table `todolist-test`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist-test`.`task` (
  `id` INT NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_time` DATETIME(6) NULL DEFAULT NULL,
  `start_time` DATETIME(6) NULL DEFAULT NULL,
  `status` BIT(1) NOT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
