SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `case_study` ;
CREATE SCHEMA IF NOT EXISTS `case_study` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `case_study` ;

-- -----------------------------------------------------
-- Table `case_study`.`USER`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_study`.`USER` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT ,
  `USER_NAME` VARCHAR(45) NOT NULL ,
  `PASSWORD` VARCHAR(60) NOT NULL ,
  `FIRST_NAME` VARCHAR(45) NOT NULL ,
  `LAST_NAME` VARCHAR(45) NOT NULL ,
  `ENABLED` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`USER_ID`) ,
  UNIQUE INDEX `USER_NAME_UNIQUE` (`USER_NAME` ASC) ,
  UNIQUE INDEX `PASSWORD_UNIQUE` (`PASSWORD` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_study`.`CATEGORY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_study`.`CATEGORY` (
  `CATEGORY_ID` INT NOT NULL AUTO_INCREMENT ,
  `CATEGORY_NAME` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`CATEGORY_ID`) ,
  UNIQUE INDEX `CATEGORY_NAME_UNIQUE` (`CATEGORY_NAME` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_study`.`COLLECTION`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_study`.`COLLECTION` (
  `COLLECTION_ID` INT NOT NULL AUTO_INCREMENT ,
  `COLLECTION_NAME` VARCHAR(45) NOT NULL ,
  `USER_ID` INT NOT NULL ,
  UNIQUE INDEX `COLLECTION_NAME_UNIQUE` (`COLLECTION_NAME` ASC) ,
  PRIMARY KEY (`COLLECTION_ID`) ,
  INDEX `fk_COLLECTION_USER1_idx` (`USER_ID` ASC) ,
  CONSTRAINT `fk_COLLECTION_USER1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `case_study`.`USER` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_study`.`MONUMENT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_study`.`MONUMENT` (
  `MONUMENT_ID` INT NOT NULL AUTO_INCREMENT ,
  `MONUMENT_NAME` VARCHAR(45) NOT NULL ,
  `MONUMENT_DESC` VARCHAR(255) NULL ,
  `COLLECTION_ID` INT NOT NULL ,
  `USER_ID` INT NOT NULL ,
  `CATEGORY_ID` INT NOT NULL ,
  PRIMARY KEY (`MONUMENT_ID`) ,
  UNIQUE INDEX `MONUMENT_NAME_UNIQUE` (`MONUMENT_NAME` ASC) ,
  INDEX `fk_MONUMENT_COLLECTION1_idx` (`COLLECTION_ID` ASC) ,
  INDEX `fk_MONUMENT_USER1_idx` (`USER_ID` ASC) ,
  INDEX `fk_MONUMENT_CATEGORY1_idx` (`CATEGORY_ID` ASC) ,
  CONSTRAINT `fk_MONUMENT_COLLECTION1`
    FOREIGN KEY (`COLLECTION_ID` )
    REFERENCES `case_study`.`COLLECTION` (`COLLECTION_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MONUMENT_USER1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `case_study`.`USER` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MONUMENT_CATEGORY1`
    FOREIGN KEY (`CATEGORY_ID` )
    REFERENCES `case_study`.`CATEGORY` (`CATEGORY_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_study`.`PICTURE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_study`.`PICTURE` (
  `PICTURE_ID` INT NOT NULL ,
  `PICTURE_NAME` VARCHAR(45) NOT NULL ,
  `PICTURE_DESC` VARCHAR(255) NULL ,
  `PICTURE_PATH` VARCHAR(255) NULL ,
  `PICTURE_DATE` DATE NULL ,
  `USER_ID` INT NOT NULL ,
  `MONUMENT_ID` INT NOT NULL ,
  UNIQUE INDEX `PICTURE_NAME_UNIQUE` (`PICTURE_NAME` ASC) ,
  PRIMARY KEY (`PICTURE_ID`) ,
  INDEX `fk_PICTURE_USER_idx` (`USER_ID` ASC) ,
  INDEX `fk_PICTURE_MONUMENT1_idx` (`MONUMENT_ID` ASC) ,
  CONSTRAINT `fk_PICTURE_USER`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `case_study`.`USER` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PICTURE_MONUMENT1`
    FOREIGN KEY (`MONUMENT_ID` )
    REFERENCES `case_study`.`MONUMENT` (`MONUMENT_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_study`.`USER_ROLE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `case_study`.`USER_ROLE` (
  `USER_ROLE_ID` INT NOT NULL AUTO_INCREMENT ,
  `ROLE` VARCHAR(45) NOT NULL ,
  `USER_ID` INT NOT NULL ,
  PRIMARY KEY (`USER_ROLE_ID`) ,
  INDEX `fk_USER_ROLE_USER1_idx` (`USER_ID` ASC) ,
  CONSTRAINT `fk_USER_ROLE_USER1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `case_study`.`USER` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
