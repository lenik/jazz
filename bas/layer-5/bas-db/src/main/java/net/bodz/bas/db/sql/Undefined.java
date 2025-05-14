package net.bodz.bas.db.sql;

public class Undefined {

    private Undefined() {
    }

    @Override
    public String toString() {
        return "undefined";
    }

    public static final Undefined INSTANCE = new Undefined();

}
