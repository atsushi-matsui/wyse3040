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
    management_nubmer VARCHAR(255) NOT NULL,
    status ENUM('ACTIVATED','USING','DEACTIVATED') NOT NULL,
    reservation_date DATE,
    return_date DATE,
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(wyse_id)
);

-- reservation_order
DROP TABLE IF EXISTS reservation_order;
CREATE TABLE IF NOT EXISTS reservation_order
(
    transaction_id INT AUTO_INCREMENT NOT NULL,
    wyse_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    status ENUM('NO_PROCESSING','COMPLETED_OK','COMPLETED_NG') NOT NULL,
    reservation_date DATE NOT NULL,
    return_date DATE NOT NULL,
    create_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(transaction_id),
    FOREIGN KEY (wyse_id) REFERENCES wyse (wyse_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);