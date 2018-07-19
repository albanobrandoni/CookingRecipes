SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `cooking` ;
CREATE SCHEMA IF NOT EXISTS `cooking` DEFAULT CHARACTER SET latin1 ;
USE `cooking` ;

-- -----------------------------------------------------
-- Table `cooking`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cooking`.`users` ;

CREATE TABLE IF NOT EXISTS `cooking`.`users` (
  `idusers` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profile` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idusers`),
  UNIQUE INDEX `name` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cooking`.`ingredients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cooking`.`ingredients` ;

CREATE TABLE IF NOT EXISTS `cooking`.`ingredients` (
  `idingredients` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `users_idusers` INT(11) NOT NULL,
  PRIMARY KEY (`idingredients`),
  UNIQUE INDEX `name_users_idusers_uidx` USING BTREE (`name` ASC, `users_idusers` ASC),
  INDEX `fk_ingredients_users1_idx` (`users_idusers` ASC),
  CONSTRAINT `fk_ingredients_users1`
    FOREIGN KEY (`users_idusers`)
    REFERENCES `cooking`.`users` (`idusers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cooking`.`recipes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cooking`.`recipes` ;

CREATE TABLE IF NOT EXISTS `cooking`.`recipes` (
  `idrecipes` INT(11) NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(45) NOT NULL,
  `comments` VARCHAR(150) NOT NULL,
  `date` VARCHAR(10) NOT NULL,
  `image` VARCHAR(250) NOT NULL,
  `serves` INT(11) NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  `time_preparation` VARCHAR(45) NOT NULL,
  `users_idusers` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `title` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`idrecipes`),
  UNIQUE INDEX `title_users_idusers_uidx` (`title` ASC, `users_idusers` ASC),
  INDEX `fk_recipes_users_idx` (`users_idusers` ASC),
  CONSTRAINT `fk_recipes_users`
    FOREIGN KEY (`users_idusers`)
    REFERENCES `cooking`.`users` (`idusers`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cooking`.`ingredients_has_recipes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cooking`.`ingredients_has_recipes` ;

CREATE TABLE IF NOT EXISTS `cooking`.`ingredients_has_recipes` (
  `ingredients_idingredients` INT(11) NOT NULL,
  `recipes_idrecipes` INT(11) NOT NULL,
  `quantity` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ingredients_idingredients`, `recipes_idrecipes`),
  INDEX `fk_ingredients_has_recipes_recipes1_idx` (`recipes_idrecipes` ASC),
  INDEX `fk_ingredients_has_recipes_ingredients1_idx` (`ingredients_idingredients` ASC),
  CONSTRAINT `fk_ingredients_has_recipes_ingredients1`
    FOREIGN KEY (`ingredients_idingredients`)
    REFERENCES `cooking`.`ingredients` (`idingredients`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ingredients_has_recipes_recipes1`
    FOREIGN KEY (`recipes_idrecipes`)
    REFERENCES `cooking`.`recipes` (`idrecipes`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cooking`.`steps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cooking`.`steps` ;

CREATE TABLE IF NOT EXISTS `cooking`.`steps` (
  `idsteps` INT(11) NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `step_order` INT(11) NOT NULL,
  `recipes_idrecipes` INT(11) NOT NULL,
  PRIMARY KEY (`idsteps`),
  INDEX `fk_steps_recipes1_idx` (`recipes_idrecipes` ASC),
  CONSTRAINT `fk_steps_recipes1`
    FOREIGN KEY (`recipes_idrecipes`)
    REFERENCES `cooking`.`recipes` (`idrecipes`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cooking`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cooking`.`type` ;

CREATE TABLE IF NOT EXISTS `cooking`.`type` (
  `idtype` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `users_idusers` INT(11) NOT NULL,
  PRIMARY KEY (`idtype`),
  UNIQUE INDEX `name_users_idusers_uidx` (`name` ASC, `users_idusers` ASC),
  INDEX `fk_type_users1_idx` (`users_idusers` ASC),
  CONSTRAINT `fk_type_users1`
    FOREIGN KEY (`users_idusers`)
    REFERENCES `cooking`.`users` (`idusers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
