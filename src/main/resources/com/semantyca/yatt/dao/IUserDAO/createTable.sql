CREATE TABLE users
            (
              id SERIAL,
              reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
              title VARCHAR(255),
              author INT NOT NULL,
              last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
              last_mod_user INT NOT NULL,
              login VARCHAR(100),
              pwd VARCHAR(100),
              email VARCHAR(100),
              name VARCHAR(255),
              roles jsonb,
              CONSTRAINT users_pkey PRIMARY KEY (id)
            );


