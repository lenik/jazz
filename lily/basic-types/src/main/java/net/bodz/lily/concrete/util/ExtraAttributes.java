package net.bodz.lily.concrete.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.bodz.bas.db.ibatis.IResultSetForm;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.typer.std.MutableAttributes;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class ExtraAttributes
        extends MutableAttributes
        implements
            IJsonForm,
            IResultSetForm {

    IEntityTypeInfo typeInfo;

    public ExtraAttributes(IEntityTypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    @Override
    public void readObject(ResultSet rs)
            throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int n = meta.getColumnCount();
        for (int i = 1; i <= n; i++) {
            String columnName = meta.getColumnName(i);

            if (columnName.contains("_")) {
                int ul = columnName.indexOf('_');
                String alias = columnName.substring(0, ul);
                // String column = columnName.substring(ul + 1);
                IProperty aliasProp = typeInfo.getPropertyForColumn(alias);
                if (aliasProp != null)
                    continue;
//                IProperty refProp = typeInfo.getProperty(alias);
//                if (refProp != null)
//                    continue;
            }

            if (typeInfo.isColumnPresent(columnName))
                continue;

            Object data = rs.getObject(i);
            if (data != null)
                setAttribute(columnName, data);
        }
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        for (String key : o.keySet()) {
            Object value = o.get(key);
            setAttribute(key, value);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        for (String name : getAttributeNames()) {
            Object value = getAttribute(name);
            out.entry(name, value);
        }
    }

}
