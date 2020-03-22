USE wyse3040;

-- user
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    user_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    status ENUM('ENTRY','EXIT') NOT NULL,
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(user_id)
);

-- wyse
DROP TABLE IF EXISTS wyse;
CREATE TABLE IF NOT EXISTS wyse
(
    wyse_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255),
    management_nubmer VARCHAR(255) NOT NULL,
    status ENUM('ACTIVATED','USING','DEACTIVATED') NOT NULL,
    reservation_date DATE,
    return_date DATE,
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(wyse_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

-- reservation
DROP TABLE IF EXISTS reservation_operation;
CREATE TABLE IF NOT EXISTS reservation_operation
(
    transaction_id VARCHAR(255) NOT NULL auto_increment,
    wyse_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    status ENUM('WAITING','COMPLETED','CANCELED') NOT NULL,
    reservation_date DATE NOT NULL,
    return_date DATE NOT NULL,
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(wyse_id, reservation_date),
    FOREIGN KEY (wyse_id) REFERENCES wyse (wyse_id),
    FOREIGN KEY (user_id) REFERENCES wyse (user_id)
);

-- return_order
DROP TABLE IF EXISTS return_operation;
CREATE TABLE IF NOT EXISTS return_operation
(
    transaction_id VARCHAR(255) NOT NULL auto_increment,
    wyse_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    status ENUM('WAITING','COMPLETED') NOT NULL,
    return_date DATE NOT NULL,
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(transaction_id),
    FOREIGN KEY (wyse_id) REFERENCES wyse (wyse_id),
    FOREIGN KEY (user_id) REFERENCES wyse (user_id)
);