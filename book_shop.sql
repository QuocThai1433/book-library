-- MySQL Script generated by MySQL Workbench
-- Wed Apr 10 13:43:47 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema book_shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema book_shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `book_shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `book_shop` ;

-- -----------------------------------------------------
-- Table `book_shop`.`readers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`readers` (
  `id` INT NOT NULL,
  `name_reader` VARCHAR(45) NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_shop`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`books` (
  `id` INT NOT NULL,
  `book_name` VARCHAR(45) NULL DEFAULT NULL,
  `publication_year` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `reader_id` INT NULL DEFAULT NULL,
  `ratingAverage` FLOAT NULL DEFAULT NULL,
  `bookscol` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `reader_id_idx` (`reader_id` ASC) VISIBLE,
  CONSTRAINT `reader_id`
    FOREIGN KEY (`reader_id`)
    REFERENCES `book_shop`.`readers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_shop`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`author` (
  `id` INT NOT NULL,
  `author_name` VARCHAR(45) NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `sach_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `sach_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_shop`.`books` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_shop`.`book_reader`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`book_reader` (
  `book_id` INT NULL DEFAULT NULL,
  `reader_id` INT NULL DEFAULT NULL,
  `borrowed_day` VARCHAR(45) NULL DEFAULT NULL,
  `return_date` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `book_id` (`book_id` ASC) VISIBLE,
  INDEX `reader_id` (`reader_id` ASC) VISIBLE,
  CONSTRAINT `book_reader_ibfk_1`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_shop`.`books` (`id`),
  CONSTRAINT `book_reader_ibfk_2`
    FOREIGN KEY (`reader_id`)
    REFERENCES `book_shop`.`readers` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_shop`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`category` (
  `id` INT NOT NULL,
  `category_name` VARCHAR(45) NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `book_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `book_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_shop`.`books` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_shop`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`publisher` (
  `id` INT NOT NULL,
  `publisher_name` VARCHAR(45) NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `book_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `b_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_shop`.`books` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_shop`.`rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_shop`.`rating` (
  `id` INT NOT NULL,
  `star_rating` INT NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  `comment` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `b_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `bok_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_shop`.`books` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
