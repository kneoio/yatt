package com.semantyca.yatt.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
*/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindJson {
    String value();
/*
    public static class JsonBinderFactory implements BinderFactory {
        @Override
        public Binder build(Annotation annotation) {
            return new Binder<BindJson, String>() {
                @Override
                public void bind(SQLStatement q, BindJson bind, String jsonString) {
                    try {
                        PGobject data = new PGobject();
                        data.setType("jsonb");
                        data.setValue(jsonString);
                        q.bind(bind.value(), data);
                    } catch (SQLException ex) {
                        throw new IllegalStateException("Error Binding JSON",ex);
                    }
                }
            };
        }
    }*/
}

