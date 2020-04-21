package com.semantyca.yatt.model.system;

import com.semantyca.yatt.model.AppEntity;

public class Role extends AppEntity<Integer> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
