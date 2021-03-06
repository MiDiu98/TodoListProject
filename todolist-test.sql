-- MySQL Script generated by MySQL Workbench
-- Fri Apr 17 14:07:05 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema todolist-test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema todolist-test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `todolistTest` DEFAULT CHARACTER SET utf8mb4;
USE `todolistTest` ;

-- -----------------------------------------------------
-- Table `todolist-test`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolistTest`.`hibernate_sequence` (
    `next_val` BIGINT NULL DEFAULT NULL)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;


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
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
