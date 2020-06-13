CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table users
(
	id SERIAL,
	reg_date TIMESTAMP with TIME zone not null,
	title VARCHAR(255),
	author INT not null,
	last_mod_date TIMESTAMP with TIME zone not null,
	last_mod_user INT not null,
	login VARCHAR(100),
	pwd VARCHAR(100),
	email VARCHAR(100),
	name VARCHAR(255),
	roles jsonb,
	constraint users_pkey primary key (id)
);

CREATE TABLE assignees
(
	  id uuid DEFAULT uuid_generate_v4(),
	  reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  title VARCHAR(255),
	  author INT NOT NULL,
	  last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  last_mod_user INT NOT NULL,
	  rank INT NOT NULL DEFAULT 999,
	  name VARCHAR(512),
	  user_id INT NOT NULL,
	  CONSTRAINT asignees_pkey PRIMARY KEY (id)
);

CREATE TABLE tasks
(
      id uuid DEFAULT uuid_generate_v4(),
      reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
      title VARCHAR(255),
      author INT NOT NULL,
      last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
      last_mod_user INT NOT NULL,
      assignee uuid,
      type INT NOT NULL,
      status INT NOT NULL,
      priority INT NOT NULL,
      description VARCHAR(512),
      deadline DATE,
      CONSTRAINT tasks_pkey PRIMARY KEY (id)
);
CREATE TABLE task_rls
(
     entity_id uuid NOT NULL,
     reader INT NOT NULL,
     reading_time TIMESTAMP WITH TIME ZONE,
     is_edit_allowed INT NOT NULL,
     UNIQUE (entity_id, reader)
);









