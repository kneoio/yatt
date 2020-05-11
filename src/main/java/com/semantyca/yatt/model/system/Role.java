package com.semantyca.yatt.model.system;

import com.semantyca.yatt.model.DataEntity;

public class Role extends DataEntity<Integer> {
    private String name;

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }
}
