package com.semantyca.yatt.shell;

import com.semantyca.yatt.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {
    public static final String format = "%-10s%s%n";

    @Autowired
    DatabaseService service;

    @Autowired
    IUserDAO userDAO;


    @ShellMethod("Initialize database")
    public String init_db() {
        if (service.init()){
            return "initialized succesfully";
        } else {
            return "something wrong happened";
        }

    }

    @ShellMethod("Populate data")
    public String populate() {
        if (service.populate()){
            return "loaded";
        } else {
            return "something wrong happened";
        }

    }

    @ShellMethod("Purge database")
    public String purge_db() {
        if (service.purge()){
            return "purged without pity";
        } else {
            return "something wrong happened";
        }

    }

    @ShellMethod("Show all users")
    public String show_users() {
        userDAO.findAll(999, 0).forEach(u -> System.out.printf(format, u.getId() + ": ", u.getLogin()));
        return "done";
    }
}
