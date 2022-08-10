package net.bodz.lily.gen.model.java;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.ITableViewMetadata;

public class Commons {

    static Map<Class<?>, String> initVals = new HashMap<>();
    static {
        initVals.put(String.class, "\"\"");
        initVals.put(BigDecimal.class, "BigDecimal.ZERO");
        initVals.put(BigInteger.class, "BigInteger.ZERO");
        initVals.put(Timestamp.class, "new Timestamp(System.currentTimeMillis()");
    }

    static void initNotNulls(ITreeOut out, ITableViewMetadata table) {
        out.println("public void initNotNulls() {");
        out.enter();
        for (IColumnMetadata column : table.getColumns()) {
            if (column.isPrimaryKey())
                continue;

            if (column.isNullable())
                continue;

            Class<?> type = column.getType();
            String initVal = Commons.initVals.get(type);
            if (initVal == null)
                continue;

            String col_name = column.getName();
            String colName = StringId.UL.toCamel(col_name);
            out.println("this." + colName + " = " + initVal + ";");
        }
        out.leave();
        out.println("}");
    }

}
