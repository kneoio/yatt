package com.semantyca.yatt.shell;

import com.semantyca.yatt.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

//@ShellComponent
public class ShellCommands {
    public static final String format = "%-10s%s%n";

    @Lazy
    @Autowired
    private Environment environment;

    @Lazy
    @Autowired
    DatabaseService service;

    @Lazy
    @Autowired
    IUserDAO userDAO;

    //@ShellMethod("Show info")
    public String info() {
        for (String  val:environment.getDefaultProfiles()){
            System.out.printf(format, val + ": ", "-");
        }
        service.info();
        return "done";
    }

    //@ShellMethod("Initialize database")
    public String init_db() {
        if (service.init()){
            return "initialized succesfully";
        } else {
            return "something wrong happened";
        }

    }

    //@ShellMethod("Populate data")
    public String populate() {
        if (service.populate()){
            return "loaded";
        } else {
            return "something wrong happened";
        }

    }

    //@ShellMethod("Purge database")
    public String purge_db() {
        if (service.purge()){
            return "purged without pity";
        } else {
            return "something wrong happened";
        }

    }

    //@ShellMethod("Show all users")
    public String show_users() {
        userDAO.findAllUnrestricted(999, 0).forEach(u -> System.out.printf(format, u.getId() + ": ", u.getLogin()));
        return "done";
    }
}
