CREATE DATABASE wateski3978_library;
USE wateski3978_library;

CREATE TABLE Author (
	AID int NOT NULL,
	AuthorName varchar(255) NOT NULL,
    PRIMARY KEY (AID)
);
CREATE TABLE Book (
	ISBN varchar(13) NOT NULL,
    BookName varchar(255),
	Rating DECIMAL(3,2),
    RatingCount int,
    Pages int,
    PublishYear int,
    PRIMARY KEY (ISBN)
);
CREATE TABLE Genre (
	GID int NOT NULL,
    GenreName varchar(255),
    PRIMARY KEY (GID)
);
CREATE TABLE Owner (
	OID int NOT NULL,
    FirstName varchar(255),
    LastName varchar(255),
    PRIMARY KEY (OID)
);
CREATE TABLE Patron (
	PID int NOT NULL,
    FirstName varchar(255),
    LastName varchar(255),
    PRIMARY KEY(PID)
);
CREATE TABLE Wrote (
	ISBN int NOT NULL,
    AID int NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
    FOREIGN KEY (AID) REFERENCES Author(AID)
);
CREATE TABLE Owns (
	ISBN int NOT NULL,
    OID int NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
    FOREIGN KEY (OID) REFERENCES Owner(OID)
);
CREATE TABLE IsGenre (
	ISBN int NOT NULL,
    GID int NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
    FOREIGN KEY (GID) REFERENCES Genre(GID)
);
CREATE TABLE Borrows (
	ISBN int NOT NULL,
    PID int NOT NULL,
    FOREIGN KEY (ISBN) REFERENCES Book(ISBN),
    FOREIGN KEY (PID) REFERENCES Patron(PID)
);