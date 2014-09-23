package net.bodz.bas.db.jdbc;

import javax.sql.DataSource;

import net.bodz.bas.rtx.IQueryable;

import com.jolbox.bonecp.BoneCPDataSource;

public class BoneCPDataSourceProvider
        implements IDataSourceProvider, IDataSourcePropertyNames {

    private BoneCPDataSource dataSource;

    public BoneCPDataSourceProvider(IQueryable queryable) {
        this(queryable.query(DataSourceArguments.class));
    }

    public BoneCPDataSourceProvider(DataSourceArguments args) {
        if (args == null)
            throw new NullPointerException("args");
        dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(args.getType().getDriverClassName());
        dataSource.setJdbcUrl(args.getConnectionUrl());
        dataSource.setUsername(args.getUserName());
        dataSource.setPassword(args.getPassword());
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
