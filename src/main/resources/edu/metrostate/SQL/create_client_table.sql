CREATE TABLE IF NOT EXISTS Client (
    clientID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    phoneNumber VARCHAR(15),
    email VARCHAR(100)
    password VARCHAR(25)
);
