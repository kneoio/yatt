CREATE TABLE users
            (
              id SERIAL,
              reg_date DATE NOT NULL,
              title VARCHAR(255),
              author INT NOT NULL,
              last_mod_date DATE NOT NULL,
              last_mod_user INT NOT NULL,
              login  VARCHAR(100),
              email VARCHAR(100),
              name VARCHAR(255),
              roles jsonb,
              CONSTRAINT users_pkey PRIMARY KEY (id)
            );


