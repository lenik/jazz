package net.bodz.bas.t.table;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.json.JsonObject;

public class DefaultTableMetadata
        extends DefaultRowSetMetadata
        implements
            ITableMetadata {

    String name;
    String label;
    String description;
    String[] primaryKey;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String[] getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String[] primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setPrimaryKey(String s) {
        if (Nullables.isEmpty(s))
            primaryKey = new String[0];
        else {
            primaryKey = s.split(",");
            for (int i = 0; i < primaryKey.length; i++)
                primaryKey[i] = primaryKey[i].trim();
        }
    }

    @Override
    public IColumnMetadata[] getPrimaryKeyColumns() {
        String[] pk = getPrimaryKey();
        IColumnMetadata[] columns = new IColumnMetadata[pk.length];
        for (int i = 0; i < pk.length; i++) {
            int column = indexOfColumn(pk[i]);
            columns[i] = getColumn(column);
        }
        return columns;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);

        name = o.getString(K_NAME);

        String s = o.getString(K_PRIMARY_KEY);
        setPrimaryKey(s);
    }

    @Override
    public void readObject(IElement x_table)
            throws ParseException, LoaderException {
        super.readObject(x_table);

        name = x_table.getAttribute("name");
        String s = x_table.getString(K_PRIMARY_KEY);
        setPrimaryKey(s);
    }

    @Override
    public String toString() {
        return name + "(" + getColumnNames() + ")";
    }

}
