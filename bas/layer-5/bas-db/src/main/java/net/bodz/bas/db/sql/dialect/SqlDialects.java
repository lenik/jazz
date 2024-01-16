package net.bodz.bas.db.sql.dialect;

public interface SqlDialects {

    ISqlDialect MIMER_SQL = new MimerSQL();
    ISqlDialect MYSQL = new MySQL();
    ISqlDialect ORACLE = new Oracle();
    ISqlDialect POSTGRESQL = new PostgreSQL();
    ISqlDialect SQL_2023 = new MimerSQL();
    ISqlDialect SQL_SERVER = new SQLServer();
    ISqlDialect TERADATA = new Teradata();

}
