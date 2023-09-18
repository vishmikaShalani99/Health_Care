-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mmsdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mmsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mmsdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mmsdb` ;

-- -----------------------------------------------------
-- Table `mmsdb`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`administrator` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmsdb`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`appointments` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Patient_Name` VARCHAR(45) NOT NULL,
  `Patient_Id` INT NOT NULL,
  `Doctor_Name` VARCHAR(45) NOT NULL,
  `Doctor_Id` INT NOT NULL,
  `Created_Date` DATE NOT NULL,
  `Appointment_Date` DATE NOT NULL,
  `Time` TIME NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmsdb`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`doctor` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Specialization` VARCHAR(45) NULL DEFAULT NULL,
  `Fee` INT NOT NULL,
  `Mobile_Number` VARCHAR(45) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmsdb`.`nurse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`nurse` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Duty_Hours` INT NOT NULL,
  `Mobile_Number` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmsdb`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`patient` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Age` INT NOT NULL,
  `Blood_Group` VARCHAR(45) NOT NULL,
  `Mobile_Number` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Address` VARCHAR(100) NULL DEFAULT NULL,
  `Symptoms` VARCHAR(150) NULL DEFAULT NULL,
  `Doctor_Record` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmsdb`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`payments` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Appointment_Id` INT NOT NULL,
  `Patient_Id` INT NOT NULL,
  `Doctor_Id` INT NOT NULL,
  `Payment_Method` VARCHAR(45) NOT NULL,
  `Payment_Date` DATE NOT NULL,
  `Doctor_Fee` INT NOT NULL,
  `Hospital_Fee` INT NOT NULL,
  `Total` FLOAT NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mmsdb`.`receptionist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmsdb`.`receptionist` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Duty_Hours` INT NULL DEFAULT NULL,
  `Mobile_Number` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
