CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA yatt;

create table yatt.users
(
	id SERIAL PRIMARY KEY,
	reg_date TIMESTAMP with TIME zone not null,
	title VARCHAR(255),
	author INT not null,
	last_mod_date TIMESTAMP with TIME zone not null,
	last_mod_user INT not null,
	login VARCHAR(100) UNIQUE,
	pwd VARCHAR(100),
	status INT NOT NULL DEFAULT 0,
	email VARCHAR(100),
	name VARCHAR(255),
	defaultLang VARCHAR(3),
	roles jsonb
);

CREATE TABLE yatt.languages
(
	  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	  reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  title VARCHAR(255),
	  author INT NOT NULL,
	  last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  last_mod_user INT NOT NULL,
	  rank INT NOT NULL DEFAULT 999,
	  is_active BOOLEAN NOT NULL DEFAULT TRUE,
	  name VARCHAR(512) UNIQUE,
	  localized_names jsonb,
	  code VARCHAR(3)
);

CREATE TABLE yatt.subscriptions
(
	  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	  reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  title VARCHAR(255),
	  author INT NOT NULL,
	  last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  last_mod_user INT NOT NULL,
	  is_active BOOLEAN NOT NULL DEFAULT TRUE,
	  trigger_type INT,
	  type VARCHAR(5) UNIQUE,
	  address VARCHAR(50)
);

CREATE TABLE yatt.assignees
(
	  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	  reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  title VARCHAR(255),
	  author INT NOT NULL,
	  last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  last_mod_user INT NOT NULL,
	  rank INT NOT NULL DEFAULT 999,
	  name VARCHAR(512),
	  user_id INT NOT NULL
);

CREATE TABLE yatt.labels
(
	  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	  reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  title VARCHAR(255),
	  author INT NOT NULL,
	  last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
	  last_mod_user INT NOT NULL,
	  rank INT NOT NULL DEFAULT 999,
	  is_active BOOLEAN NOT NULL DEFAULT TRUE,
	  name VARCHAR(512) UNIQUE,
	  localized_names jsonb,
	  category VARCHAR(512),
	  color INT NOT NULL
);

CREATE TABLE yatt.tasks
(
      id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
      reg_date TIMESTAMP WITH TIME ZONE NOT NULL,
      title VARCHAR(255),
      author INT NOT NULL,
      last_mod_date TIMESTAMP WITH TIME ZONE NOT NULL,
      last_mod_user INT NOT NULL,
      reg_number VARCHAR(64),
      assignee uuid,
      type INT NOT NULL,
      status INT NOT NULL,
      priority INT NOT NULL,
      description VARCHAR(512),
      deadline DATE,
      impl_description VARCHAR(512)
);

CREATE TABLE yatt.task_rls
(
     entity_id uuid NOT NULL,
     reader INT NOT NULL,
     reading_time TIMESTAMP WITH TIME ZONE,
     is_edit_allowed INT NOT NULL,
     PRIMARY KEY (entity_id, reader)
);


CREATE TABLE yatt.task_labels
(
     entity_id uuid NOT NULL,
     label_id INT NOT NULL,
     PRIMARY KEY (entity_id, label_id)
);



INSERT INTO yatt.users (reg_date, author, last_mod_date, last_mod_user, status, login, pwd, roles)
VALUES (current_timestamp , 1, current_timestamp, 0, 0, 'test1','$2a$10$CDfPJUCxyHtIVv9jrPX7AeGmdg7qxzVOwUdUpVFwFWU5ylivzCvHm','["supervisor"]')