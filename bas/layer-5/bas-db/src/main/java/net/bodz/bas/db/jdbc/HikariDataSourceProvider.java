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

        HikariDataSource hikari = new HikariDataSource();
        // hikari.setDriverClass(args.getType().getDriverClassName());
        hikari.setAllowPoolSuspension(true);
        hikari.setRegisterMbeans(true);
        hikari.setJdbcUrl(options.getConnectionUrl());
        hikari.setDataSourceProperties(options.getProperties());
        hikari.setUsername(options.getUserName());
        hikari.setPassword(options.getPassword());
        hikari.setConnectionTimeout(60 * 1000L); // 1 min
        hikari.setMaximumPoolSize(options.getPoolSize());
        hikari.setMaxLifetime(300 * 1000); // 5 min
        // hikari.setConnectionTimeout(250);

        P6DataSource p6 = new P6DataSource(hikari);
        this.dataSource = p6;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
