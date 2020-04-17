package com.semantyca.yatt.model;


import com.semantyca.yatt.model.embedded.RLS;

import java.util.*;

public abstract class SecureAppEntity extends AppEntity<Integer> {

    private Set<Integer> editors = new HashSet<>();

    private Map<Integer, RLS> readers = new HashMap<>();

    public Set<Integer> getEditors() {
        return editors;
    }

    public void setEditors(Set<Integer> editors) {
        this.editors = editors;
    }

    public Map<Integer, RLS> getReaders() {
        return readers;
    }

    public void setReaders(Map<Integer, RLS> readers) {
        this.readers = readers;
    }

    public SecureAppEntity addReader(RLS reader){
        readers.put(reader.getReader(), reader);
        return this;
    }

    @Override
    public boolean isEditable() {
        return super.isEditable();
    }


}
