package com.semantyca.yatt.service;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssigneeService {

    @Lazy
    @Autowired
    IAssigneeDAO assigneeDAO;

    public long getCountOfAll() {
        return assigneeDAO.getCountAll();
    }

    public ViewPage findAll(String pageSizeAsString, String pageNumAsString) {
        long count = getCountOfAll();
        int size = NumberUtil.stringToInt(pageSizeAsString, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNumAsString, 0);
        int startEntry = NumberUtil.calcStartEntry(num, size);
        List<Assignee> result = assigneeDAO.findAllUnrestricted(size, startEntry);
        return new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size);
    }

    public Assignee findById(UUID id) {
         return assigneeDAO.findById(id);
    }

}
