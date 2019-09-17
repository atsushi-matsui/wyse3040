-- user
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
    user_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    create_datetime  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(user_id)
);

-- wyse
DROP TABLE IF EXISTS wyse;
CREATE TABLE IF NOT EXISTS wyse (
    wyse_id VARCHAR(255) NOT NULL,
    management_nubmer VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    create_datetime  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(wyse_id)
);

-- reserved_wyse
DROP TABLE IF EXISTS reserved_wyse;
CREATE TABLE IF NOT EXISTS reserved_wyse (
    wyse_id VARCHAR(255) NOT NULL,
    reservable_date DATE NOT NULL,
    create_datetime  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(wyse_id, reservable_date),
    FOREIGN KEY (wyse_id) REFERENCES wyse (wyse_id)
    );


-- reservable_wyse
DROP TABLE IF EXISTS reservable_wyse;
CREATE TABLE IF NOT EXISTS reservable_wyse (
    transaction_id INT AUTO_INCREMENT NOT NULL,
    wyse_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    reserved_date DATE NOT NULL,
    release_date VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    create_datetime  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(transaction_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (wyse_id, reserved_date) REFERENCES reserved_wyse (wyse_id, reservable_date)
    );