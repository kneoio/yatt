package com.semantyca.yatt.controller.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.semantyca.yatt.configuration.ApplicationContextKeeper;
import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.model.Assignee;

import java.io.IOException;
import java.util.UUID;

public class AssigneeDeserializer extends StdDeserializer<Assignee> {

    public AssigneeDeserializer() {
        this(null);
    }

    public AssigneeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Assignee deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        TreeNode treeNode = jp.getCodec().readTree(jp);
        UUID id = UUID.fromString(treeNode.toString());
        IAssigneeDAO assigneeDAO = ApplicationContextKeeper.getContext().getBean(IAssigneeDAO.class);
        return assigneeDAO.findById(id);
    }
}
