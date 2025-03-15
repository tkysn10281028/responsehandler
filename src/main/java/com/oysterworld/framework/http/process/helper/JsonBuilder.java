package com.oysterworld.framework.http.process.helper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JsonBuilder {
    public String writeValueAsString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return "\"" + escapeJson((String) obj) + "\"";
        }
        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof Collection) {
            return writeCollection((Collection<?>) obj);
        }
        if (obj.getClass().isArray()) {
            return writeArray(obj);
        }
        if (obj instanceof Map) {
            return writeMap((Map<?, ?>) obj);
        }

        return writeObject(obj);
    }

    private String writeCollection(Collection<?> collection) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            json.append(writeValueAsString(iterator.next()));
            if (iterator.hasNext()) {
                json.append(", ");
            }
        }
        json.append("]");
        return json.toString();
    }

    private String writeArray(Object array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            json.append(writeValueAsString(Array.get(array, i)));
            if (i < length - 1) {
                json.append(", ");
            }
        }
        json.append("]");
        return json.toString();
    }

    private String writeMap(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) {
                json.append(", ");
            }
            json.append(writeValueAsString(entry.getKey())).append(": ").append(writeValueAsString(entry.getValue()));
            first = false;
        }
        json.append("}");
        return json.toString();
    }

    private String writeObject(Object obj) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        Field[] fields = obj.getClass().getDeclaredFields();
        boolean first = true;

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null) {
                    if (!first) {
                        json.append(", ");
                    }
                    json.append("\"").append(field.getName()).append("\": ");
                    json.append(writeValueAsString(value));
                    first = false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("JSON変換エラー", e);
            }
        }

        json.append("}");
        return json.toString();
    }

    private String escapeJson(String value) {
        return value.replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
