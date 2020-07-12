DROP TRIGGER  IF EXISTS  changing_tracker  ON yatt.users;
DROP TRIGGER  IF EXISTS  changing_tracker  ON yatt.tasks;
DROP FUNCTION  IF EXISTS  change_trigger;

DROP SCHEMA doclog CASCADE;
