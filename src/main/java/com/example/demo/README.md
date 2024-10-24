Welcome to RobotSimulator!

All controllers have a corresponding fxml file.

ex. RulesetsController -- Rulesets.fxml


Recommended resolution is 1920 X 1080
________________________________
--Create table queries
CREATE TABLE layouts (
    layout_id INT PRIMARY KEY DEFAULT nextval('layout_is_seq'),
    layout_name VARCHAR(50) NOT NULL UNIQUE,
    layout_data VARCHAR[],
    direction VARCHAR(10) NOT NULL,
    email_address VARCHAR(50),
    CONSTRAINT fk_email
        FOREIGN KEY (email_address)
        REFERENCES useraccounts (email_address)
);

CREATE TABLE useraccounts (
user_id SERIAL PRIMARY KEY,
full_name VARCHAR(50) NOT NULL ,
email_address VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(50) NOT NULL
);
_________________________________
CREATE SEQUENCE layout_id_seq
    START 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 5
    CYCLE;

ALTER TABLE layouts ADD CONSTRAINT fk_email
    FOREIGN KEY (email_address)
        REFERENCES useraccounts (email_address);

-- First, remove the old primary key constraint
ALTER TABLE layouts DROP CONSTRAINT layouts_pkey;
-- Change the column type (if needed, though it's usually already INT if SERIAL was used)
ALTER TABLE layouts ALTER COLUMN layout_id TYPE INT;
-- Set the default value using the sequence
ALTER TABLE layouts ALTER COLUMN layout_id SET DEFAULT nextval('layout_id_seq');
-- Re-add the primary key constraint
ALTER TABLE layouts ADD PRIMARY KEY (layout_id);