package net.bodz.bas.t.catalog;

import java.sql.SQLException;
import java.util.Iterator;

import net.bodz.bas.db.meta.RowSetType;
import net.bodz.bas.io.IPrintOut;

public class RowSetPrinter
        extends TablePrinter<RowSetPrinter, IRowSetMetadata, IRow> {

    public RowSetPrinter(IPrintOut out)
            throws SQLException {
        super(out, RowSetType.INSTANCE);
    }

    public void print(IRowSet rs)
            throws SQLException {
        Iterator<? extends IRow> iterator = rs.getRows().iterator();

        super.print(rs.getMetadata(), () -> {
            if (iterator.hasNext())
                return iterator.next();
            else
                return null;
        });
    }

}
