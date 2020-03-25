package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.model.IAppEntity;
import com.semantyca.yatt.model.SecureAppEntity;
import com.semantyca.yatt.model.embedded.Reader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"kind", "readers", "editors"})
public class ACL {

    public Map<Long, Object> readers = new HashMap<>();
    public Map<Long, Object> editors = new HashMap<>();



    public ACL(IAppEntity<Integer> e) {
        SecureAppEntity entity = (SecureAppEntity) e;

        Map<Long, Reader> readerMap = entity.getReaders();
        if (readerMap != null) {
            for (Map.Entry<Long, Reader> reader : readerMap.entrySet()) {
                Date readingTime = reader.getValue().getReadingTime();
                if (readingTime != null) {
                    readers.put(reader.getKey(), getUserName(reader.getKey()) + " - " + readingTime.toString());
                } else {
                    readers.put(reader.getKey(), getUserName(reader.getKey()));
                }
            }
        }

        for (Long id : entity.getEditors()) {
            editors.put(id, getUserName(id));
        }
    }


    private String getUserName(long id) {
        return Long.toString(id);
    }
}
