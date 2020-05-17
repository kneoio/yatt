package com.semantyca.yatt.dto;

import com.semantyca.yatt.dto.constant.PayloadType;

import java.util.Map;

public interface IOutcome<T> {

    String getIdentifier();

    OutcomeType getType();

    String getTitle();

    String getPageName();

    Map<String, Object> getPayloads();

    T addPayload(PayloadType exception, Object anything);

    T setPageName(String pageName) ;
}
