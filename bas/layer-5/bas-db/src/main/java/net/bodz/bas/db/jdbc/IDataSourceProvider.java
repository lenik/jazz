package net.bodz.bas.db.jdbc;

import javax.sql.DataSource;

public interface IDataSourceProvider {

    String ATTRIBUTE_KEY = IDataSourceProvider.class.getName();

    DataSource getDataSource();

}
