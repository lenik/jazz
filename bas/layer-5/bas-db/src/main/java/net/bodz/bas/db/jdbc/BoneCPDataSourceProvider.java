package net.bodz.bas.db.jdbc;

import javax.sql.DataSource;

import net.bodz.bas.rtx.IQueryable;

import com.jolbox.bonecp.BoneCPDataSource;

public class BoneCPDataSourceProvider
        implements
            IDataSourceProvider,
            IDataSourcePropertyNames {

    private BoneCPDataSource dataSource;

    public BoneCPDataSourceProvider(IQueryable queryable) {
        this(queryable.query(ConnectOptions.class));
    }

    public BoneCPDataSourceProvider(ConnectOptions args) {
        if (args == null)
            throw new NullPointerException("args");
        dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(args.getType().getDriverClassName());
        dataSource.setJdbcUrl(args.getConnectionUrl());
        dataSource.setUsername(args.getUserName());
        dataSource.setPassword(args.getPassword());

        // dataSource.setIdleConnectionTestPeriodInMinutes(2); // 4 hr
        // dataSource.setIdleMaxAge(2, TimeUnit.HOURS); // 1 hr
        // dataSource.setMaxConnectionsPerPartition(60); // 10
        // dataSource.setMinConnectionsPerPartition(20); // 0
        // dataSource.setPartitionCount(3); // 1
        // dataSource.setAcquireIncrement(10); // 2
        // dataSource.setStatementsCacheSize(50); // 0
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
