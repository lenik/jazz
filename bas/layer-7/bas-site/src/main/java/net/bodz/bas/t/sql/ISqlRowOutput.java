package net.bodz.bas.t.sql;

import java.sql.Date;

public interface ISqlRowOutput {

    void putEntry(String name, Object value);

    void putDateEntry(String name, Date date);

    // void putRawEntry(String name, String s);

}
