package com.semantyca.yatt.configuration;

import com.semantyca.yatt.dao.IDAO;
import com.semantyca.yatt.dao.RepositoryDAO;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.jackson2.Jackson2Plugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class PrimaryConfiguration {
    private boolean dataEngineIsOk;
    private static final Logger log = LoggerFactory.getLogger(PrimaryConfiguration.class);
    private static final String UUID_PLUGIN_NAME = "uuid-ossp";


    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbiPlugins.forEach(plugin -> jdbi.installPlugin(plugin));
        jdbi.installPlugin(new PostgresPlugin());
        jdbi.installPlugin(new Jackson2Plugin());
        //ObjectMapper objectMapper = new ObjectMapper();
        //jdbi.getConfig(Jackson2Config.class).setMapper(objectMapper);
        rowMappers.forEach(mapper -> jdbi.registerRowMapper(mapper));
        return jdbi;
    }


    @Bean
    public JdbiPlugin sqlObjectPlugin() {
        return new SqlObjectPlugin();
    }


    @Bean
    @Autowired
    public DataEngineService dataEnginService(Jdbi jdbi) {
        DataEngineService dataEngineService = jdbi.onDemand(DataEngineService.class);
        String plugin = dataEngineService.getPlugin(UUID_PLUGIN_NAME);
        if (plugin == null) {
            log.error("no " + UUID_PLUGIN_NAME + " extension");
            //System.exit(0);
        }
        List<String> tables = dataEngineService.findAllTables();
        Map<String, Class<? extends IDAO>> involvedTables = getAllDAOs();
        for (Map.Entry<String, Class<? extends IDAO>> involvedTable : involvedTables.entrySet()) {
            if (!tables.contains(involvedTable.getKey())) {
                IDAO dao = jdbi.onDemand(involvedTable.getValue());
                log.info("create table " + involvedTable.getKey());
            //    dao.createTable();
            }
        }
        dataEngineIsOk = true;
        return dataEngineService;
    }


    private static Map<String, Class<? extends IDAO>> getAllDAOs() {
        Map<String, Class<? extends IDAO>> daos = new HashMap<>();

        String appPackageName = "com.semantyca.yatt.dao";
        Reflections appReflections = new Reflections(appPackageName);
        Set<Class<?>> appClasses = appReflections.getTypesAnnotatedWith(RepositoryDAO.class);
        for (Class<?> taskClass : appClasses) {
            RepositoryDAO commandAnotation = taskClass.getAnnotation(RepositoryDAO.class);
            for(String involvedTable: commandAnotation.value()){
                daos.put(involvedTable, (Class<? extends IDAO>) taskClass);
            }
        }
        return daos;

    }




}
