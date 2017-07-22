package net.bodz.bas.db.jdbc;

import java.util.Map;

import net.bodz.bas.c.java.util.regex.UnixStyleVarExpander;
import net.bodz.bas.db.sql.dialect.ISqlFormat;
import net.bodz.bas.db.sql.dialect.SqlFormats;
import net.bodz.bas.potato.ref.PropertyRefMap;
import net.bodz.bas.potato.ref.ValueEntry;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;

public class DatabaseType
        extends Predef<DatabaseType, String> {

    private static final long serialVersionUID = 1L;

    public static final PredefMetadata<DatabaseType, String> meta = PredefMetadata.forClass(DatabaseType.class);

    private final String hibernateDialect;
    private final String driverClass;
    private final String urlFormat;
    private final ISqlFormat sqlFormat;

    private DatabaseType(String name, String hibernateDialect, String driverClass, String urlFormat,
            ISqlFormat sqlFormat) {
        super(name, name, meta);
        this.hibernateDialect = hibernateDialect;
        this.driverClass = driverClass;
        this.urlFormat = urlFormat;
        this.sqlFormat = sqlFormat;
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

    public ISqlFormat getSqlFormat() {
        return sqlFormat;
    }

    /**
     * H2 Embedded Database
     */
    public static final DatabaseType H2 = new DatabaseType("H2", //
            "org.hibernate.dialect.H2Dialect", //
            "org.h2.Driver", //
            "jdbc:h2://${rootDir}/${database};DB_CLOSE_ON_EXIT=FALSE", //
            SqlFormats.DEFAULT);

    /**
     * HSQL Embedded Database
     */
    public static final DatabaseType HSQL = new DatabaseType("HSQL",//
            "org.hibernate.dialect.HSQLDialect", //
            "org.hsql.Driver", //
            "jdbc:hsql://${rootDir}/${database}", //
            SqlFormats.DEFAULT);

    /**
     * PostgreSQL RDBMS
     */
    public static final DatabaseType POSTGRESQL = new DatabaseType("POSTGRESQL", //
            "org.hibernate.dialect.PostgreSQLDialect", //
            "org.postgresql.Driver", //
            "jdbc:postgresql://${server}/${database}", //
            SqlFormats.POSTGRESQL);

    /**
     * Oracle Enterprise Database
     */
    public static final DatabaseType ORACLE = new DatabaseType("ORACLE",//
            "org.hibernate.dialect.OracleDialect", //
            "oracle.jdbc.driver.OracleDriver", //
            "jdbc:oracle://${server}/${database}", //
            SqlFormats.ORACLE);

    /**
     * MySQL RDBMS
     */
    public static final DatabaseType MYSQL = new DatabaseType("MYSQL",//
            "org.hibernate.dialect.MySQLDialect", //
            "org.mysql.Driver", //
            "jdbc:mysql://${server}/${database}", //
            SqlFormats.MYSQL);

}