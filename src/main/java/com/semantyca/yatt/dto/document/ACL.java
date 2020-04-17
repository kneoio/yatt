package com.semantyca.yatt.dto.document;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.model.IAppEntity;
import com.semantyca.yatt.model.SecureAppEntity;
import com.semantyca.yatt.model.embedded.RLS;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"kind", "readers", "editors"})
public class ACL {

    public Map<Integer, Object> readers = new HashMap<>();
    public Map<Integer, Object> editors = new HashMap<>();



    public ACL(IAppEntity<Integer> e) {
        SecureAppEntity entity = (SecureAppEntity) e;

        Map<Integer, RLS> readerMap = entity.getReaders();
        if (readerMap != null) {
            for (Map.Entry<Integer, RLS> reader : readerMap.entrySet()) {
                Date readingTime = reader.getValue().getReadingTime();
                if (readingTime != null) {
                    readers.put(reader.getKey(), getUserName(reader.getKey()) + " - " + readingTime.toString());
                } else {
                    readers.put(reader.getKey(), getUserName(reader.getKey()));
                }
            }
        }

        for (Integer id : entity.getEditors()) {
            editors.put(id, getUserName(id));
        }
    }


    private String getUserName(long id) {
        return Long.toString(id);
    }
}
