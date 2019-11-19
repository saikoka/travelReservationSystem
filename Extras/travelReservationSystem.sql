-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema travelReservationSystemV1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema travelReservationSystemV1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travelReservationSystemV1` DEFAULT CHARACTER SET latin1 ;
USE `travelReservationSystemV1` ;

-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Users` (
  `UserID` VARCHAR(20) NOT NULL,
  `Pass` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Admin` (
  `UserID` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `Admin_ibfk_1`
    FOREIGN KEY (`UserID`)
    REFERENCES `travelReservationSystemV1`.`Users` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Airline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Airline` (
  `AirlineID` INT(11) NOT NULL,
  PRIMARY KEY (`AirlineID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Aircraft`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Aircraft` (
  `AircraftID` INT(11) NOT NULL,
  `AirlineID` INT(11) NOT NULL,
  PRIMARY KEY (`AircraftID`, `AirlineID`),
  INDEX `AirlineID` (`AirlineID` ASC),
  CONSTRAINT `Aircraft_ibfk_1`
    FOREIGN KEY (`AirlineID`)
    REFERENCES `travelReservationSystemV1`.`Airline` (`AirlineID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Airport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Airport` (
  `AirportID` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`AirportID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Customer` (
  `UserID` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `Customer_ibfk_1`
    FOREIGN KEY (`UserID`)
    REFERENCES `travelReservationSystemV1`.`Users` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`CustomerRep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`CustomerRep` (
  `UserID` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `CustomerRep_ibfk_1`
    FOREIGN KEY (`UserID`)
    REFERENCES `travelReservationSystemV1`.`Users` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Flight` (
  `FlightNumber` INT(11) NOT NULL,
  `ArrivalTime` DATETIME NULL DEFAULT NULL,
  `DepartureTime` DATETIME NULL DEFAULT NULL,
  `ArrivalAirport` VARCHAR(3) NULL DEFAULT NULL,
  `DepartureAirport` VARCHAR(3) NULL DEFAULT NULL,
  `Aircraft` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`FlightNumber`),
  INDEX `ArrivaleAirport` (`ArrivalAirport` ASC),
  INDEX `DepartureAirport` (`DepartureAirport` ASC),
  INDEX `Aircraft` (`Aircraft` ASC),
  CONSTRAINT `Flight_ibfk_1`
    FOREIGN KEY (`ArrivalAirport`)
    REFERENCES `travelReservationSystemV1`.`Airport` (`AirportID`),
  CONSTRAINT `Flight_ibfk_2`
    FOREIGN KEY (`DepartureAirport`)
    REFERENCES `travelReservationSystemV1`.`Airport` (`AirportID`),
  CONSTRAINT `Flight_ibfk_3`
    FOREIGN KEY (`Aircraft`)
    REFERENCES `travelReservationSystemV1`.`Aircraft` (`AircraftID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Operates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Operates` (
  `AirlineID` INT(11) NOT NULL,
  `FlightNumber` INT(11) NOT NULL,
  `Sun` BIT(1) NULL DEFAULT NULL,
  `Mon` BIT(1) NULL DEFAULT NULL,
  `Tues` BIT(1) NULL DEFAULT NULL,
  `Wed` BIT(1) NULL DEFAULT NULL,
  `Thur` BIT(1) NULL DEFAULT NULL,
  `Fri` BIT(1) NULL DEFAULT NULL,
  `Sat` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`AirlineID`, `FlightNumber`),
  INDEX `FlightNumber` (`FlightNumber` ASC),
  CONSTRAINT `Operates_ibfk_1`
    FOREIGN KEY (`AirlineID`)
    REFERENCES `travelReservationSystemV1`.`Airline` (`AirlineID`),
  CONSTRAINT `Operates_ibfk_2`
    FOREIGN KEY (`FlightNumber`)
    REFERENCES `travelReservationSystemV1`.`Flight` (`FlightNumber`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Reservation` (
  `ReservationID` INT(11) NOT NULL,
  `UserID` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ReservationID`),
  INDEX `UserID` (`UserID` ASC),
  CONSTRAINT `Reservation_ibfk_1`
    FOREIGN KEY (`UserID`)
    REFERENCES `travelReservationSystemV1`.`Users` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`Ticket` (
  `TicketID` INT(11) NOT NULL,
  `BookingFee` DOUBLE NULL DEFAULT NULL,
  `SeatNumber` INT(11) NULL DEFAULT NULL,
  `Class` VARCHAR(8) NULL DEFAULT NULL,
  `Meal` VARCHAR(20) NULL DEFAULT NULL,
  `FlightNumber` INT(11) NULL DEFAULT NULL,
  `ReservationID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`TicketID`),
  INDEX `FlightNumber` (`FlightNumber` ASC),
  INDEX `ReservationID` (`ReservationID` ASC),
  CONSTRAINT `Ticket_ibfk_1`
    FOREIGN KEY (`FlightNumber`)
    REFERENCES `travelReservationSystemV1`.`Flight` (`FlightNumber`),
  CONSTRAINT `Ticket_ibfk_2`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `travelReservationSystemV1`.`Reservation` (`ReservationID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `travelReservationSystemV1`.`WaitingList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travelReservationSystemV1`.`WaitingList` (
  `FlightNumber` INT(11) NOT NULL,
  `ReservationID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`FlightNumber`),
  INDEX `ReservationID` (`ReservationID` ASC),
  CONSTRAINT `WaitingList_ibfk_1`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `travelReservationSystemV1`.`Reservation` (`ReservationID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
