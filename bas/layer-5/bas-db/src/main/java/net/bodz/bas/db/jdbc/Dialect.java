package net.bodz.bas.db.jdbc;

import java.util.Map;

import net.bodz.bas.c.java.util.regex.UnixStyleVarExpander;
import net.bodz.bas.potato.ref.PropertyRefMap;
import net.bodz.bas.potato.ref.ValueEntry;
import net.bodz.bas.t.enm.Enum;
import net.bodz.bas.t.enm.EnumMetadata;

public class Dialect
        extends Enum<Dialect, String> {

    private static final long serialVersionUID = 1L;

    public static final EnumMetadata<Dialect, String> METADATA = EnumMetadata.forClass(Dialect.class);

    private final String hibernateDialect;
    private final String driverClass;
    private final String urlFormat;

    private Dialect(String name, String hibernateDialect, String driverClass, String urlFormat) {
        super(name, name, METADATA);
        this.hibernateDialect = hibernateDialect;
        this.driverClass = driverClass;
        this.urlFormat = urlFormat;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrlFormat() {
        return urlFormat;
    }

    public String getConnectionUrl(Map<?, ?> properties) {
        UnixStyleVarExpander expander = new UnixStyleVarExpander(properties);
        String url = expander.process(urlFormat);
        return url;
    }

    public String getConnectionUrl(Object obj) {
        PropertyRefMap refMap = new PropertyRefMap(ValueEntry.wrap(obj), null);
        return getConnectionUrl(refMap);
    }

    /**
     * H2 Embedded Database
     */
    public static final Dialect H2 = new Dialect("h2", //
            "org.hibernate.dialect.H2Dialect", //
            "org.h2.Driver", //
            "jdbc:h2://${rootDir}/${database};DB_CLOSE_ON_EXIT=FALSE");

    /**
     * HSQL Embedded Database
     */
    public static final Dialect HSQL = new Dialect("hsql",//
            "org.hibernate.dialect.HSQLDialect", //
            "org.hsql.Driver", //
            "jdbc:hsql://${rootDir}/${database}");

    /**
     * PostgreSQL RDBMS
     */
    public static final Dialect PostgreSQL = new Dialect("postgresql", //
            "org.hibernate.dialect.PostgreSQLDialect", //
            "org.postgresql.Driver", //
            "jdbc:postgresql://${server}/${database}");

    /**
     * Oracle Enterprise Database
     */
    public static final Dialect Oracle = new Dialect("oracle",//
            "org.hibernate.dialect.OracleDialect", //
            "com.oracle.jdbc.Driver", //
            "jdbc:oracle://${server}/${database}");

    /**
     * MySQL RDBMS
     */
    public static final Dialect MySQL = new Dialect("mysql",//
            "org.hibernate.dialect.MySQLDialect", //
            "org.mysql.Driver", //
            "jdbc:mysql://${server}/${database}");

}