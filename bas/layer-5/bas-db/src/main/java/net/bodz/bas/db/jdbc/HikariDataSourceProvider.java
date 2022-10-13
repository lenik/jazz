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

    public HikariDataSourceProvider(ConnectOptions options) {
        if (options == null)
            throw new NullPointerException("options");

        HikariDataSource dataSource = new HikariDataSource();
        // dataSource.setDriverClass(args.getType().getDriverClassName());
        dataSource.setJdbcUrl(options.getConnectionUrl());
        dataSource.setDataSourceProperties(options.getProperties());
        dataSource.setUsername(options.getUserName());
        dataSource.setPassword(options.getPassword());
        dataSource.setConnectionTimeout(60 * 1000L); // 1 min
        dataSource.setMaximumPoolSize(options.getPoolSize());

        P6DataSource p6 = new P6DataSource(dataSource);
        this.dataSource = p6;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
