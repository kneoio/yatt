package com.semantyca.yatt.model;


import com.semantyca.yatt.model.embedded.Reader;

import java.util.*;

public abstract class SecureAppEntity extends AppEntity<Integer> {

    private Set<Long> editors = new HashSet<>();

    private Map<Long, Reader> readers = new HashMap<>();

    public Set<Long> getEditors() {
        return editors;
    }

    public void setEditors(Set<Long> editors) {
        this.editors = editors;
    }


    public Map<Long, Reader> getReaders() {
        return readers;
    }

    public void setReaders(Map<Long, Reader> readers) {
        this.readers = readers;
    }

    @Override
    public boolean isEditable() {
        return super.isEditable();
    }
}
