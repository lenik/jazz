package net.bodz.lily.tool.javagen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.rst.IRstForm;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.fmt.rst.RstFn;
import net.bodz.bas.fmt.rst.StackRstHandler;
import net.bodz.bas.json.JsonObject;

public class TableViewConfig
        implements
            IRstForm,
            IJsonForm {

    private static final String K_JAVA_NAME = "javaName";
    private static final String K_JAVA_TYPE = "javaType";
    private static final String K_DESCRIPTION = "description";
    private static final String K_COLUMNS = "columns";
    private static final String K_COLUMN = "column";

    public String javaName;
    public String javaType;
    public String description;

    public final Map<String, ColumnConfig> columns = new HashMap<>();

    ColumnConfig resolveColumn(String name) {
        ColumnConfig column = columns.get(name);
        if (column == null)
            columns.put(name, column = new ColumnConfig());
        return column;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        javaName = o.getString(K_JAVA_NAME);
        javaType = o.getString(K_JAVA_TYPE);
        description = o.getString(K_DESCRIPTION);

        JsonObject j_columns = o.getJsonObject(K_COLUMNS);
        if (j_columns != null) {
            for (String k : j_columns.keySet()) {
                JsonObject j_column = j_columns.getJsonObject(k);
                ColumnConfig column = resolveColumn(k);
                column.jsonIn(j_column, opts);
            }
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entryNotNull(K_JAVA_NAME, this.javaName);
        out.entryNotNull(K_JAVA_TYPE, this.javaType);
        out.entryNotNull(K_DESCRIPTION, this.description);

        if (columns != null && !columns.isEmpty()) {
            out.key(K_COLUMNS);
            out.object();
            for (String k : columns.keySet()) {
                ColumnConfig column = columns.get(k);
                out.key(k);
                column.jsonOut(out, opts);
            }
            out.endObject();
        }
    }

    @Override
    public IRstHandler getElementHandler() {
        return new StackRstHandler() {

            @Override
            public IRstHandler beginChild(String name, String[] args)
                    throws ParseException, ElementHandlerException {
                super.beginChild(name, args);
                switch (name) {
                case K_COLUMN:
                    if (args.length < 1)
                        throw new IllegalArgumentException("expect column name");
                    String columnName = args[0];
                    ColumnConfig column = resolveColumn(columnName);
                    return column.getElementHandler();
                }
                return this;
            }

            @Override
            public boolean attribute(String name, String data)
                    throws ParseException, ElementHandlerException {
                switch (name) {
                case K_JAVA_NAME:
                    javaName = data;
                    return true;

                case K_JAVA_TYPE:
                    javaType = data;
                    return true;

                case K_DESCRIPTION:
                    description = data;
                    return true;

                default:
                    return false;
                }
            }
        };
    }

    @Override
    public void writeObject(IRstOutput out)
            throws IOException, FormatException {
        if (javaName != null)
            out.attribute(K_JAVA_NAME, this.javaName);
        if (javaType != null)
            out.attribute(K_JAVA_TYPE, this.javaType);
        if (description != null)
            out.attribute(K_DESCRIPTION, this.description);

        if (columns != null && !columns.isEmpty()) {
            for (String k : columns.keySet()) {
                ColumnConfig column = columns.get(k);
                out.beginElement(K_COLUMN, k);
                column.writeObject(out);
                out.endElement();
            }
        }
    }

    public static void main(String[] args)
            throws ElementHandlerException, IOException, ParseException {
        TableViewConfig tvc = new TableViewConfig();
        ColumnConfig foo = tvc.resolveColumn("foo");
        foo.description = "foo bar";
        ColumnConfig age = tvc.resolveColumn("age");
        age.description = "how old are you?";
        String rst = RstFn.toString(tvc);
        System.out.println(rst);
        System.out.println("-----------");

        TableViewConfig renew = new TableViewConfig();
        RstFn.loadFromRst(renew, rst);
        String other = RstFn.toString(renew);
        System.out.println(other);
    }

}
