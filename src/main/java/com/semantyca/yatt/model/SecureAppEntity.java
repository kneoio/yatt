package com.semantyca.yatt.model;

import com.semantyca.yatt.model.embedded.RLSEntry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class SecureAppEntity<T> extends AppEntity<T> {

    private Map<Integer, RLSEntry> readers = new HashMap<>();

    public Collection<RLSEntry> getReaders() {
        return readers.values();
    }

    public SecureAppEntity addReader(RLSEntry reader){
        readers.put(reader.getReader(), reader);
        return this;
    }

}
