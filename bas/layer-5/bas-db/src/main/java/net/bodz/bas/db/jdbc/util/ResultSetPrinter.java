package net.bodz.bas.db.jdbc.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.bodz.bas.db.meta.ResultSetType;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.catalog.TablePrinter;

public class ResultSetPrinter
        extends TablePrinter<ResultSetPrinter, ResultSetMetaData, ResultSet> {

    public ResultSetPrinter(IPrintOut out)
            throws SQLException {
        super(out, ResultSetType.INSTANCE);
    }

    public void print(ResultSet rs)
            throws SQLException {
        super.print(rs.getMetaData(), () -> {
            if (rs.next())
                return rs;
            else
                return null;
        });
    }

}
