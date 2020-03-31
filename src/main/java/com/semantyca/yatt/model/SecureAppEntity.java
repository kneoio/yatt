package com.semantyca.yatt.model;


import com.semantyca.yatt.model.embedded.Reader;

import java.util.*;

public abstract class SecureAppEntity extends AppEntity<Integer> {

    private Set<Integer> editors = new HashSet<>();

    private Map<Integer, Reader> readers = new HashMap<>();

    public Set<Integer> getEditors() {
        return editors;
    }

    public void setEditors(Set<Integer> editors) {
        this.editors = editors;
    }


    public Map<Integer, Reader> getReaders() {
        return readers;
    }

    public void setReaders(Map<Integer, Reader> readers) {
        this.readers = readers;
    }

    public SecureAppEntity addReader(Reader reader){
        readers.put(reader.getReader(), reader);
        return this;
    }

    @Override
    public boolean isEditable() {
        return super.isEditable();
    }


}
