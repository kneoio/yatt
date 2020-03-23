package com.semantyca.yatt.dao;

public interface IDAO {
    String SCHEMA = "public";

    String ENTITY_DDL_PIECE =
            "  id uuid DEFAULT uuid_generate_v4()," +
            "  reg_date timestamp with time zone NOT NULL," +
            "  title character varying(255)," +
            "  author bigint NOT NULL," +
            "  last_mod_date timestamp with time zone NOT NULL," +
            "  last_mod_user bigint NOT NULL,";




}
