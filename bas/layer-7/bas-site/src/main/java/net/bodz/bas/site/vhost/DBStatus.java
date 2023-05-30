package net.bodz.bas.site.vhost;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStatus {

    String name;
    long scanTime = System.currentTimeMillis();

    boolean exists;

    long dbCreationTime;
    long dbModifiedTime;

    public DBStatus() {
    }

    public static DBStatus notExisted() {
        return new DBStatus();
    }

    String encoding;
    String collate;
    String ctype;
    boolean isTemplate;
    boolean allowConnect;
    int connectionLimit;

    public static DBStatus pg_database(ResultSet rs)
            throws SQLException {
        DBStatus status = new DBStatus();
        status.exists = true;
        status.name = rs.getString("datname");
        status.encoding = rs.getString("encname");
        status.collate = rs.getString("datcollate");
        status.ctype = rs.getString("datctype");
        status.isTemplate = rs.getBoolean("datistemplate");
        status.allowConnect = rs.getBoolean("datallowconn");
        status.connectionLimit = rs.getInt("datconnlimit");
        return status;
    }

}
