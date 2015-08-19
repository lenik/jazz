package net.bodz.bas.tex.dom;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.object.Nullables;

public class Command {

    private String name;
    private Map<String, String> parameters;
    private String data;

    public Command(String name) {
        setName(name);
        this.parameters = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        toString(sb);
        return sb.toString();
    }

    public void toString(StringBuilder sb) {
        sb.append('\\');
        sb.append(name);
        if (!parameters.isEmpty()) {
            sb.append('[');
            for (Entry<String, String> entry : parameters.entrySet()) {
                sb.append(entry.getKey());
                String val = entry.getValue();
                if (!Nullables.isEmpty(val)) {
                    sb.append('=');
                    sb.append(val);
                }
            }
            sb.append(']');
        }
        if (data != null) {
            sb.append('{');
            sb.append(data);
            sb.append("}");
        }
    }

}
