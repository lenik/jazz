package net.bodz.lily.tool.javagen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.json.JsonObject;

public class CodegenConfig
        implements
            IRstForm,
            IJsonForm {

    static final String K_COLUMN_PROPERTY = "column-property";
    static final String K_TABLE_CLASS = "table-class";
    static final String K_COLUMN_LEVEL = "column-level";
    static final String K_JOIN_LEVEL = "join-level";

    Map<String, String> columnPropertyMap = new HashMap<>();
    Map<String, String> tableClassMap = new HashMap<>();
    Map<String, Integer> columnLevelMap = new HashMap<>();
    Map<String, Integer> joinLevelMap = new HashMap<>();

    @Override
    public void writeObject(IRstOutput out)
            throws IOException, FormatException {
        out.beginElement(K_COLUMN_PROPERTY);
        for (String column : columnPropertyMap.keySet()) {
            String property = columnPropertyMap.get(column);
            out.attribute(column, property);
        }
        out.endElement();

        out.beginElement(K_TABLE_CLASS);
        for (String table : tableClassMap.keySet()) {
            String className = tableClassMap.get(table);
            out.attribute(table, className);
        }
        out.endElement();

        out.beginElement(K_COLUMN_LEVEL);
        for (String column : columnLevelMap.keySet()) {
            Integer level = columnLevelMap.get(column);
            out.attribute(column, level);
        }
        out.endElement();

        out.beginElement(K_JOIN_LEVEL);
        for (String column : joinLevelMap.keySet()) {
            Integer depth = joinLevelMap.get(column);
            out.attribute(column, depth);
        }
        out.endElement();
    }

    @Override
    public IRstHandler getElementHandler() {
        return new IRstHandler() {
            String parent;

            @Override
            public IRstHandler beginChild(String name, String[] args)
                    throws ParseException, ElementHandlerException {
                parent = name;
                return this;
            }

            @Override
            public boolean attribute(String name, String data)
                    throws ParseException, ElementHandlerException {
                switch (parent) {
                case K_COLUMN_PROPERTY:
                    columnPropertyMap.put(name, data.trim());
                    return true;

                case K_TABLE_CLASS:
                    tableClassMap.put(name, data.trim());
                    return true;

                case K_COLUMN_LEVEL:
                    int level = Integer.parseInt(data.trim());
                    columnLevelMap.put(name, level);
                    return true;

                case K_JOIN_LEVEL:
                    int depth = Integer.parseInt(data.trim());
                    joinLevelMap.put(name, depth);
                    return true;

                default:
                    return false;
                }
            }
        };
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.key(K_COLUMN_PROPERTY);
        out.map(columnPropertyMap);

        out.key(K_TABLE_CLASS);
        out.map(tableClassMap);

        out.key(K_COLUMN_LEVEL);
        out.map(columnLevelMap);

        out.key(K_JOIN_LEVEL);
        out.map(joinLevelMap);
    }

}
