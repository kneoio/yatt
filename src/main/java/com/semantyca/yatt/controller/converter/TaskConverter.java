package com.semantyca.yatt.controller.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.semantyca.yatt.model.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

//@JsonComponent
public class TaskConverter {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);

    public static class Serialize extends JsonSerializer<ZonedDateTime> {
        @Override
        public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider) {
            try {
                if (value == null) {
                    jgen.writeNull();
                }
                else {
                    jgen.writeString(DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault()).format(value) );
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static class Deserialize extends JsonDeserializer<Task> {

        @Override
        public Task deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            try {
                String dateAsString = jp.getText();


                TreeNode treeNode = jp.getCodec().readTree(jp);
                int id = (Integer) ((IntNode) treeNode.get("assigneeId")).numberValue();
                TextNode favoriteColor = (TextNode) treeNode.get("assigneeId");
                System.out.println(favoriteColor);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return new Task();
        }
    }
}
