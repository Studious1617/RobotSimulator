Welcome to RobotSimulator!

All controllers have a corresponding fxml file.

ex. RulesetsController -- Rulesets.fxml


Recommended resolution is 1920 X 1080
________________________________
--Create table queries
CREATE TABLE layouts (
layout_id SERIAL PRIMARY KEY,
layout_name VARCHAR(50) NOT NULL UNIQUE,
layout_data VARCHAR[],
direction VARCHAR(10) NOT NULL,
email_address VARCHAR(50)
);

CREATE TABLE useraccounts (
user_id SERIAL PRIMARY KEY,
full_name VARCHAR(50) NOT NULL ,
email_address VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(50) NOT NULL
);
_________________________________
