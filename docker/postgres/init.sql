CREATE USER equipment;

CREATE DATABASE "equipment-dev";
CREATE DATABASE "equipment-test";

GRANT ALL PRIVILEGES ON DATABASE "equipment-dev"  TO equipment;
GRANT ALL PRIVILEGES ON DATABASE "equipment-test" TO equipment;
