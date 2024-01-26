package net.bodz.bas.db.ibatis.sql;

import org.apache.commons.text.StringTokenizer;

public class Order {

    String column;
    boolean ascending;

    public Order() {
    }

    public Order(String column) {
        this(column, true);
    }

    public Order(String column, boolean ascending) {
        if (column == null)
            throw new NullPointerException("column");
        this.column = column;
        this.ascending = ascending;
    }

    public static Order parse(String s) {
        if (s == null)
            return null;
        s = s.trim();

        // ~field, -field: descending
        if (s.startsWith("~") || s.startsWith("-")) {
            String column = s.substring(1);
            return new Order(column, false);
        }

        // field+: ascending
        if (s.endsWith("+")) {
            String column = s.substring(0, s.length() - 1);
            return new Order(column, true);
        }

        // field-: ascending
        if (s.endsWith("-")) {
            String column = s.substring(0, s.length() - 1);
            return new Order(column, false);
        }

        // field asc (or) field desc
        StringTokenizer tokens = new StringTokenizer(s, ",\t\n\r");
        String column = tokens.nextToken();
        if (column == null) // assert false
            return null;
        String mod = tokens.nextToken();
        if (mod == null)
            return new Order(s);

        boolean asc = true;
        switch (mod.toLowerCase()) {
        case "a":
        case "asc":
        case "ascend":
            asc = true;
            break;
        case "d":
        case "desc":
        case "descend":
            asc = false;
            break;
        default:
            return new Order(s);
        }
        return new Order(column, asc);
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
        if (! ascending) {
            sb.append(" desc");
        }
        return sb.toString();
    }

}
