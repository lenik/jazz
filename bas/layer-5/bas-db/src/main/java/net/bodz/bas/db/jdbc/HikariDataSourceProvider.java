package net.bodz.bas.db.jdbc;

import javax.sql.DataSource;

import net.bodz.bas.rtx.IQueryable;

import com.p6spy.engine.spy.P6DataSource;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceProvider
        implements
            IDataSourceProvider,
            IDataSourcePropertyNames {

    private DataSource dataSource;

    public HikariDataSourceProvider(IQueryable queryable) {
        this(queryable.query(ConnectOptions.class));
    }

    public HikariDataSourceProvider(ConnectOptions args) {
        if (args == null)
            throw new NullPointerException("args");

        HikariDataSource dataSource = new HikariDataSource();
        // dataSource.setDriverClass(args.getType().getDriverClassName());
        dataSource.setJdbcUrl(args.getConnectionUrl());
        dataSource.setUsername(args.getUserName());
        dataSource.setPassword(args.getPassword());
        dataSource.setConnectionTimeout(60 * 1000L); // 1 min

        P6DataSource p6 = new P6DataSource(dataSource);
        this.dataSource = p6;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
