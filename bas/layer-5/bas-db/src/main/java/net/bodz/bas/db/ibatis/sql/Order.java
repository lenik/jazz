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
        // s=s.trim();
        if (s.endsWith("+")) {
            String column = s.substring(0, s.length() - 1);
            return new Order(column, true);
        }
        if (s.endsWith("-")) {
            String column = s.substring(0, s.length() - 1);
            return new Order(column, false);
        }
        StringTokenizer tokens = new StringTokenizer(s, " \t\n\r");
        String column = tokens.nextToken();
        if (column == null) // assert false
            return null;
        String mod = tokens.nextToken();
        if (mod == null)
            return new Order(s);

        boolean asc = true;
        switch (mod.toLowerCase()) {
        case "asc":
        case "ascend":
            asc = true;
            break;
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
        if (!ascending) {
            sb.append(" desc");
        }
        return sb.toString();
    }

}
