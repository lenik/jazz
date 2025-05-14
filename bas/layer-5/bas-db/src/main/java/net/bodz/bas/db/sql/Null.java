package net.bodz.bas.db.sql;

public class Null {

    private Null() {
    }

    @Override
    public String toString() {
        return "null";
    }

    public static final Null INSTANCE = new Null();

}
