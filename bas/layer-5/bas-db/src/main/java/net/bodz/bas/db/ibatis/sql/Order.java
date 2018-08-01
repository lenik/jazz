package net.bodz.bas.db.ibatis.sql;

public class Order {

    String column;
    boolean ascending;

    public Order() {
    }

    public Order(String column, boolean ascending) {
        if (column == null)
            throw new NullPointerException("column");
        this.column = column;
        this.ascending = ascending;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        if (column == null)
            throw new NullPointerException("column");
        this.column = column;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        sb.append(column);
        sb.append('"');
        if (!ascending) {
            sb.append(" desc");
        }
        return sb.toString();
    }

}
