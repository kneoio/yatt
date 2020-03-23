package com.semantyca.yatt.dao;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.model.AnonymousUser;
import com.semantyca.yatt.model.Task;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.UUID;

public interface TaskDAO extends IDAO {
    String TABLE_NAME = SCHEMA + ".__tasks";
    String EDITORS_TABLE_NAME = SCHEMA + "." + EnvConst.CODE + "__task_editors";
    String READERS_TABLE_NAME = SCHEMA + "." + EnvConst.CODE + "__task_readers";
    String MAJOR_SELECT_CONDITION = "SELECT c.id, c.name, c.path, c.author, c.reg_date, c.last_mod_user, c.last_mod_date, c.title, s.name as source, r.reading_time as read_time," +
            "(SELECT json_agg(nl) FROM al__composition_labels as ncl, pl__labels as nl WHERE c.id = ncl.composition_id AND ncl.label_id = nl.id ) as labels," +
            "(SELECT count(nce.entity_id) FROM al__composition_editors as nce WHERE c.id = nce.entity_id) as is_editable ";

    @SqlQuery(MAJOR_SELECT_CONDITION +
            "FROM " + TABLE_NAME + " as c, " + TABLE_NAME + " as s, " + READERS_TABLE_NAME + " as r " +
            "WHERE c.source = s.id AND r.entity_id = c.id AND r.reader = " + AnonymousUser.ID + " AND c.name = :name")
    @RegisterColumnMapper(TaskMapper.class)
    Task findByName(@Bind("name") String name);

    @SqlQuery(MAJOR_SELECT_CONDITION +
            "FROM " + TABLE_NAME + " as c, " + TABLE_NAME + " as s, " + READERS_TABLE_NAME + " as r " +
            "WHERE c.source = s.id AND r.entity_id = c.id AND r.reader = :reader AND c.name = :name")
    @RegisterColumnMapper(TaskMapper.class)
    Task findByName(@Bind("name") String name, @Bind("reader") long reader);

    @SqlQuery(MAJOR_SELECT_CONDITION +
            "FROM " + TABLE_NAME + " as c, " + TABLE_NAME + " as s, " + READERS_TABLE_NAME + " as r " +
            "WHERE c.source = s.id AND r.entity_id = c.id AND r.reader = :reader AND c.id = :identifier")
    @RegisterColumnMapper(TaskMapper.class)
    Task findByIdentifier(@Bind("identifier") UUID name, @Bind("reader") long reader);

    @SqlQuery(MAJOR_SELECT_CONDITION +
            "FROM " + TABLE_NAME + " as c, " + TABLE_NAME + " as s, " + READERS_TABLE_NAME + " as r " +
            "WHERE c.source = s.id AND r.entity_id = c.id AND r.reader = :reader LIMIT :limit OFFSET :offset")
    @RegisterColumnMapper(TaskMapper.class)
    List<Task> findAll(@Bind("limit") int limit, @Bind("offset") int offset, @Bind("reader") long reader);

    @SqlQuery("SELECT count(c.id) FROM " + TABLE_NAME + " as c, " + TABLE_NAME + " as s, " + READERS_TABLE_NAME + " as r " +
            "WHERE c.source = s.id AND r.entity_id = c.id AND r.entity_id = c.id AND r.reader = :reader")
    long getCountOfAll(@Bind("reader") long reader);


    @SqlUpdate("INSERT INTO " + TABLE_NAME + " (source, name, path, last_mod_date, last_mod_user, reg_date, title, author)" +
            " VALUES (:getSourceId, :getName, :getPath, :getLastModifiedDate, :getLastModifier, :getRegDate, :getTitle, :getAuthor)")
    @GetGeneratedKeys("id")
    UUID insert(@BindMethods Task task);

    @SqlUpdate("UPDATE " + TABLE_NAME + " SET source = :getSourceId, title = :getTitle WHERE id = :getId")
    @GetGeneratedKeys
    UUID update(@BindMethods Task task);

    @SqlUpdate("CREATE TABLE " + TABLE_NAME +
            "(" +
            "  name character varying(255)," +
            "  path character varying(512)," +
            "  source uuid NOT NULL REFERENCES " + TABLE_NAME + " MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION," +
            ENTITY_DDL_PIECE +
            "  CONSTRAINT " + TABLE_NAME + "_pkey PRIMARY KEY (id)" +
            ");" +
            "CREATE TABLE " + EDITORS_TABLE_NAME +
            "(" +
            "  entity_id uuid NOT NULL REFERENCES " + TABLE_NAME + "  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION," +
            "  editor bigint NOT NULL," +
            "  UNIQUE (entity_id, editor)" +
            ");" +
            "CREATE TABLE " + READERS_TABLE_NAME +
            "(" +
            "  entity_id uuid NOT NULL REFERENCES " + TABLE_NAME + " MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION," +
            "  reader bigint NOT NULL," +
            "  reading_time timestamp with time zone," +
            "  UNIQUE (entity_id, reader)" +
            ");")
    void createTable();
}



