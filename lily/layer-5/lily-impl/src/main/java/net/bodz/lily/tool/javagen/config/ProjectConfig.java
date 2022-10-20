package net.bodz.lily.tool.javagen.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c.string.StringArray;
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
import net.bodz.bas.t.map.ListMap;

public class ProjectConfig
        implements
            IRstForm,
            IJsonForm {

    static final String K_COLUMN_PROPERTY = "column-property";
    static final String K_TABLE_NAME = "table-name";
    static final String K_CLASS_MAP = "class-map";
    static final String K_COLUMN_LEVEL = "column-level";
    static final String K_JOIN_LEVEL = "join-level";
    static final String K_TABLES = "tables";
    static final String K_TABLE = "table";
    static final String K_MIXINS = "mixins";
    static final String K_MIXIN = "mixin";

    public String defaultPackageName;

    public final Map<String, String> columnPropertyMap = new HashMap<>();
    public final Map<String, String> tableNameMap = new HashMap<>();
    public final ListMap<String, String> classMap = new ListMap<>();
    public final Map<String, Integer> columnLevelMap = new HashMap<>();
    public final Map<String, Integer> joinLevelMap = new HashMap<>();

    public final Map<String, TableViewSettings> tableMap = new LinkedHashMap<>();

    // cache
    Map<String, String> tableClassMap;
    Map<String, MixinSettings> mixinMap;

    List<String> resolveTableList(String className) {
        List<String> list = classMap.get(className);
        if (list == null) {
            list = new ArrayList<>();
            classMap.put(className, list);
        }
        return list;
    }

    TableViewSettings resolveTable(String tableName) {
        TableViewSettings table = tableMap.get(tableName);
        if (table == null)
            tableMap.put(tableName, table = new TableViewSettings());
        return table;
    }

    MixinSettings resolveMixin(String mixinName) {
        MixinSettings mixin = mixinMap.get(mixinName);
        if (mixin == null) {
            mixin = new MixinSettings();
            mixinMap.put(mixinName, mixin);
        }
        return mixin;
    }

    @Override
    public void writeObject(IRstOutput out)
            throws IOException, FormatException {
        out.beginElement(K_COLUMN_PROPERTY);
        for (String column : columnPropertyMap.keySet()) {
            String property = columnPropertyMap.get(column);
            out.attribute(column, property);
        }
        out.endElement();

        out.beginElement(K_TABLE_NAME);
        for (String table : tableNameMap.keySet()) {
            String javaName = tableNameMap.get(table);
            out.attribute(table, javaName);
        }
        out.endElement();

        out.beginElement(K_CLASS_MAP);
        for (String type : classMap.keySet()) {
            List<String> tables = classMap.get(type);
            String tableList = StringArray.join(", ", tables);
            out.attribute(type, tableList);
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

        for (String tableName : tableMap.keySet()) {
            out.beginElement(K_MIXIN, tableName);
            TableViewSettings table = tableMap.get(tableName);
            table.writeObject(out);
            out.endElement();
        }

        for (String mixinName : mixinMap.keySet()) {
            out.beginElement(K_MIXIN, mixinName);
            MixinSettings mixin = mixinMap.get(mixinName);
            mixin.writeObject(out);
            out.endElement();
        }
    }

    public synchronized Map<String, String> getTableClassMap() {
        if (tableClassMap == null) {
            tableClassMap = new HashMap<>();
            for (String type : classMap.keySet())
                for (String table : classMap.get(type))
                    tableClassMap.put(table, type);
        }
        return tableClassMap;
    }

    @Override
    public IRstHandler getElementHandler() {
        return new IRstHandler() {
            String parent;

            @Override
            public IRstHandler beginChild(String name, String[] args)
                    throws ParseException, ElementHandlerException {
                this.parent = name;
                switch (name) {
                case K_TABLE:
                    if (args.length != 1)
                        throw new ParseException("expect table name");
                    String tableName = args[0].trim();
                    TableViewSettings table = resolveTable(tableName);
                    return table.getElementHandler();

                case K_MIXIN:
                    if (args.length != 1)
                        throw new ParseException("expect mixin name");
                    String mixinName = args[0].trim();
                    MixinSettings mixin = resolveMixin(mixinName);
                    return mixin.getElementHandler();
                }
                return this;
            }

            @Override
            public boolean attribute(String name, String data)
                    throws ParseException, ElementHandlerException {
                switch (parent) {
                case K_COLUMN_PROPERTY:
                    columnPropertyMap.put(name, data.trim());
                    return true;

                case K_TABLE_NAME:
                    tableNameMap.put(name, data.trim());
                    return true;

                case K_CLASS_MAP:
                    for (String token : data.split(",")) {
                        resolveTableList(name).add(token.trim());
                    }
                    tableClassMap = null;
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

        out.key(K_TABLE_NAME);
        out.map(tableNameMap);

        out.key(K_CLASS_MAP);
        out.map(classMap);

        out.key(K_COLUMN_LEVEL);
        out.map(columnLevelMap);

        out.key(K_JOIN_LEVEL);
        out.map(joinLevelMap);

        out.key(K_TABLES);
        out.map(tableMap);

        out.key(K_MIXINS);
        out.map(mixinMap);
    }

}
