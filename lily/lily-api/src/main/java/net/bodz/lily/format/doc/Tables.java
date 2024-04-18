package net.bodz.lily.format.doc;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.doc.node.Table;
import net.bodz.bas.doc.node.TableCell;
import net.bodz.bas.doc.node.TableRow;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.t.catalog.ICell;
import net.bodz.bas.t.catalog.IColumnMetadata;
import net.bodz.bas.t.catalog.IRow;
import net.bodz.bas.t.catalog.IRowSet;
import net.bodz.bas.t.catalog.IRowSetMetadata;
import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.ui.css3.property.TextAlignMode;

public class Tables {

    public static void convert(Table table, IRowSet rows) {
        IRowSetMetadata metadata = rows.getMetadata();
        int nColumn = metadata.getColumnCount();

        // column title
        TableRow headRow = table.rows.append();
        for (int i = 0; i < nColumn; i++) {
            IColumnMetadata column = metadata.getColumn(i);
            String label = column.getLabel();
            if (label == null)
                label = column.getName();
            TableCell th = headRow.cells.append();
            th.setText(label);

            Class<?> javaClass = column.getJavaClass();
            Class<?> boxed = Primitives.box(javaClass);
            boolean isNum = Number.class.isAssignableFrom(boxed);
            TextAlignMode align = isNum ? TextAlignMode.right : TextAlignMode.left;
            HorizAlignment ha = HorizAlignment.LEFT;
            switch (align) {
            case left:
            default:
                ha = HorizAlignment.LEFT;
                break;
            case right:
                ha = HorizAlignment.RIGHT;
                break;
            case center:
                ha = HorizAlignment.CENTER;
                break;
            }
            th.setAlignment(ha);
        }

        for (IRow row : rows.getRows()) {
            TableRow tr = table.rows.append();

            for (int i = 0; i < nColumn; i++) {
                // IColumnMetadata column = metadata.getColumn(i);
                ICell cell = null;
                if (i < row.getCellCount())
                    cell = row.getCell(i);

                String str = null;
                if (cell != null) {
                    IVariant data = cell.getDataVar();
                    str = data.getString();
                }

                TableCell th = headRow.cells.get(i);
                TableCell td = tr.cells.append();
                td.setAlignment(th.getAlignment());
                td.setText(str);
            }
        }
    }

}
