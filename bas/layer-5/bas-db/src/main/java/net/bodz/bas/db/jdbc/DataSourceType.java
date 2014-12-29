package net.bodz.bas.db.jdbc;

import java.util.Map;

import net.bodz.bas.c.java.util.regex.UnixStyleVarExpander;
import net.bodz.bas.potato.ref.PropertyRefMap;
import net.bodz.bas.potato.ref.ValueEntry;
import net.bodz.bas.repr.util.Predef;
import net.bodz.bas.repr.util.PredefMetadata;

public class DataSourceType
        extends Predef<DataSourceType, String> {

    private static final long serialVersionUID = 1L;

    public static final PredefMetadata<DataSourceType, String> METADATA = PredefMetadata.forClass(DataSourceType.class);

    private final String hibernateDialect;
    private final String driverClass;
    private final String urlFormat;

    private DataSourceType(String name, String hibernateDialect, String driverClass, String urlFormat) {
        super(name, name, METADATA);
        this.hibernateDialect = hibernateDialect;
        this.driverClass = driverClass;
        this.urlFormat = urlFormat;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getDriverClassName() {
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
    public static final DataSourceType H2 = new DataSourceType("h2", //
            "org.hibernate.dialect.H2Dialect", //
            "org.h2.Driver", //
            "jdbc:h2://${rootDir}/${database};DB_CLOSE_ON_EXIT=FALSE");

    /**
     * HSQL Embedded Database
     */
    public static final DataSourceType HSQL = new DataSourceType("hsql",//
            "org.hibernate.dialect.HSQLDialect", //
            "org.hsql.Driver", //
            "jdbc:hsql://${rootDir}/${database}");

    /**
     * PostgreSQL RDBMS
     */
    public static final DataSourceType PostgreSQL = new DataSourceType("postgresql", //
            "org.hibernate.dialect.PostgreSQLDialect", //
            "org.postgresql.Driver", //
            "jdbc:postgresql://${server}/${database}");

    /**
     * Oracle Enterprise Database
     */
    public static final DataSourceType Oracle = new DataSourceType("oracle",//
            "org.hibernate.dialect.OracleDialect", //
            "com.oracle.jdbc.Driver", //
            "jdbc:oracle://${server}/${database}");

    /**
     * MySQL RDBMS
     */
    public static final DataSourceType MySQL = new DataSourceType("mysql",//
            "org.hibernate.dialect.MySQLDialect", //
            "org.mysql.Driver", //
            "jdbc:mysql://${server}/${database}");

}