CREATE TABLE assignees
            (
              id INT IDENTITY,
              reg_date DATE NOT NULL,
              title VARCHAR(255),
              author INT NOT NULL,
              last_mod_date DATE NOT NULL,
              last_mod_user INT NOT NULL,
              rank INT NOT NULL DEFAULT 999,
              name VARCHAR(512),
              user_id INT NOT NULL,
              CONSTRAINT asignees_pkey PRIMARY KEY (id)
            );



