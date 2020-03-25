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
              description VARCHAR(512),
              status INT NOT NULL,
              deadline DATE,
              CONSTRAINT tasks_pkey PRIMARY KEY (id)
            );



