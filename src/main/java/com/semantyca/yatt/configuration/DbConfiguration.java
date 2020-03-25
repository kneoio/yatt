package com.semantyca.yatt.configuration;

import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.shell.DatabaseService;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DbConfiguration {


    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbiPlugins.forEach(plugin -> jdbi.installPlugin(plugin));
        rowMappers.forEach(mapper -> jdbi.registerRowMapper(mapper));
        return jdbi;
    }


    @Bean
    public JdbiPlugin sqlObjectPlugin() {
        return new SqlObjectPlugin();
    }

    @Bean
    @Autowired
    public IUserDAO userDao(Jdbi jdbi) {
        return jdbi.onDemand(IUserDAO.class);
    }

    @Bean
    @Autowired
    public ITaskDAO taskDao(Jdbi jdbi) {
        return jdbi.onDemand(ITaskDAO.class);
    }

    @Bean
    @Autowired
    public IAssigneeDAO assigneeDAO(Jdbi jdbi) {
        return jdbi.onDemand(IAssigneeDAO.class);
    }

    @Bean
    public DatabaseService dbService( ITaskDAO taskDao){
        return new DatabaseService();
    }
}
