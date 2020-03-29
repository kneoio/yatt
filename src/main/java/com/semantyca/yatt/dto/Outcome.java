package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.EnvConst;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Outcome {
    protected OutcomeType type = OutcomeType.DEFAULT;
    protected String title = EnvConst.APP_ID;
    protected String pageName = "";
    protected ViewPage viewPage;

    public Outcome() {

    }

    public Outcome setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getPageName() {
        return pageName;
    }

    public Outcome setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public Outcome setViewPage(ViewPage viewPage) {
        this.viewPage = viewPage;
        type = OutcomeType.VIEW_PAGE;
        return this;
    }

    public OutcomeType getType() {
        return type;
    }

    public ViewPage getViewPage() {
        return viewPage;
    }

    public String toString() {
        return "type=" + type + ", title=" + title;
    }

}
