Welcome to RobotSimulator!

All controllers have a corresponding fxml file.

ex. RulesetsController -- Rulesets.fxml


Recommended resolution is 1920 X 1080
________________________________
Delete layouts table and the sequence below
---------------------------------

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
Delete this sequence

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
____________________________________
CREATE OR REPLACE FUNCTION adjust_layout_ids(user_email VARCHAR)
RETURNS VOID AS $$
BEGIN
WITH min_layout AS (
SELECT MIN(layout_id) as min_id FROM layouts WHERE email_address = user_email
)
UPDATE layouts
SET layout_id = layout_id + 1
WHERE email_address = user_email
AND layout_id < 5
AND layout_id < (SELECT min_id FROM min_layout);
END;
$$ LANGUAGE plpgsql;
_________________________________
Can follow instruction after this
---------------------------------