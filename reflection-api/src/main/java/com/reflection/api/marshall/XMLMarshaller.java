package com.reflection.api.marshall;

import java.lang.reflect.Field;
import java.util.Collection;

import com.reflection.api.annotation.NameTagXml;

public class XMLMarshaller {

    public String marshall(Object object) {
        try {
            Class<?> objetClass = object.getClass();
            StringBuilder xmlBuilder = new StringBuilder();

            if(object instanceof Collection){
                Collection<?> collection = (Collection<?>) object;
                xmlBuilder.append("<list>");

                for (Object o : collection){
                    String xml = marshall(o);
                    xmlBuilder.append(xml);
                }

                xmlBuilder.append("</list>");

            }else{
                NameTagXml classAnnotation = objetClass.getDeclaredAnnotation(NameTagXml.class);

                String className = classAnnotation == null
                		? objetClass.getName()
                        : classAnnotation.value();

                xmlBuilder.append("<" + className+">");

                for (Field field: objetClass.getDeclaredFields()) {
                    field.setAccessible(true);

                    NameTagXml fieldAnnotation = field.getDeclaredAnnotation(NameTagXml.class);

                    Object fieldName = fieldAnnotation == null
                            ? field.get(object)
                            : fieldAnnotation.value();

                    Object fieldValue = field.get(object); 

                    xmlBuilder.append("<" + fieldName + ">");
                    xmlBuilder.append(fieldValue);
                    xmlBuilder.append("</" + fieldName + ">");
                }

                xmlBuilder.append("</" + className+">");
            }

            return xmlBuilder.toString();

        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("XML Marshalling ERROR");
        }
    }

}