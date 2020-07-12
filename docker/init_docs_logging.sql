CREATE SCHEMA doclog;

CREATE TABLE doclog.doc_history (
    id          SERIAL,
    action_time TIMESTAMP  DEFAULT now(),
    table_name  TEXT,
    operation   TEXT,
    modifier    INT,
    new_val     JSONB,
    old_val     JSONB
);


CREATE FUNCTION change_trigger() RETURNS trigger AS $$
        BEGIN
            IF TG_OP = 'INSERT'
            THEN
              INSERT INTO doclog.doc_history (table_name, operation, new_val)
                            VALUES (TG_RELNAME,  TG_OP, row_to_json(NEW));
                    RETURN NEW;
            ELSIF TG_OP = 'UPDATE'
            THEN
              INSERT INTO doclog.doc_history (table_name,  operation, new_val, old_val)
                            VALUES (TG_RELNAME,  TG_OP,  row_to_json(NEW), row_to_json(OLD));
                    RETURN NEW;
            ELSIF TG_OP = 'DELETE'
            THEN
              INSERT INTO doclog.doc_history (table_name,  operation, old_val)
                            VALUES (TG_RELNAME,  TG_OP, row_to_json(OLD));
                    RETURN OLD;
            END IF;
        END;
$$ LANGUAGE 'plpgsql' SECURITY DEFINER;


CREATE TRIGGER changing_tracker BEFORE INSERT OR UPDATE OR DELETE ON yatt.users FOR EACH ROW EXECUTE PROCEDURE change_trigger();
CREATE TRIGGER changing_tracker BEFORE INSERT OR UPDATE OR DELETE ON yatt.tasks FOR EACH ROW EXECUTE PROCEDURE change_trigger();