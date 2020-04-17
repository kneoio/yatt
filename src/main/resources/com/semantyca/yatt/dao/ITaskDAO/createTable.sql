CREATE TABLE tasks
            (
                  id INT IDENTITY,
                  reg_date DATE NOT NULL,
                  title VARCHAR(255),
                  author INT NOT NULL,
                  last_mod_date DATE NOT NULL,
                  last_mod_user INT NOT NULL,
                  assignee INT,
                  type INT NOT NULL,
                  status INT NOT NULL,
                  stage INT NOT NULL,
                  description VARCHAR(512),
                  deadline DATE,
                  CONSTRAINT tasks_pkey PRIMARY KEY (id)
            );
CREATE TABLE task_rls
            (
                 entity_id INT NOT NULL,
                 reader INT NOT NULL,
                 reading_time timestamp with time zone,
                 is_edit_allowed INT NOT NULL,
                 UNIQUE (entity_id, reader)
            );



