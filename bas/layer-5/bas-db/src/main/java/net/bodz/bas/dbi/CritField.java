package net.bodz.bas.dbi;

import net.bodz.bas.potato.element.IProperty;

public class CritField {

    String columnName;
    IProperty property;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public IProperty getProperty() {
        return property;
    }

    public void setProperty(IProperty property) {
        this.property = property;
    }

}
