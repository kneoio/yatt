package com.semantyca.yatt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public Map<Integer, RLS> getReaders() {
        return readers;
    }

    public SecureAppEntity addReader(RLS reader){
        readers.put(reader.getReader(), reader);
        return this;
    }

    @Override
    public boolean isEditable() {
        return super.isEditable();
    }


    public void setReaders(RLS rls) {
        Map readersMap = new HashMap();
        readersMap.put(rls.getReader(), rls);
        this.readers =  readersMap;
    }
}
