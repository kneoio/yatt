package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semantyca.yatt.model.embedded.RLSEntry;
import com.semantyca.yatt.model.exception.RLSIsNotNormalized;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class SecureDataEntity<T> extends DataEntity<T> {

    private Map<Integer, RLSEntry> readers = new HashMap<>();

    @JsonGetter("acl")
    public Collection<RLSEntry> getReaders() {
        return readers.values();
    }

    @JsonIgnore
    public SecureDataEntity addReader(RLSEntry reader){
        readers.put(reader.getReader(), reader);
        return this;
    }


    @JsonIgnore
    public RLSEntry getRLS(int reader) throws RLSIsNotNormalized {
        if (readers != null) {
            return readers.get(reader);
        } else {
            throw new RLSIsNotNormalized();
        }
    }
}
