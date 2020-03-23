package com.semantyca.yatt.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {



    @ShellMethod("Initialize database")
    public String initdb() {
        DatabaseService service = new DatabaseService();
        return service.init();
    }
}
