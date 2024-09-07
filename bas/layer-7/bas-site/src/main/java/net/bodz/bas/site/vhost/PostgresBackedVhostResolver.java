package net.bodz.bas.site.vhost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class PostgresBackedVhostResolver
        extends MutableVirtualHostResolver {

    static final Logger logger = LoggerFactory.getLogger(PostgresBackedVhostResolver.class);

    private DataContext master;
    private ConnectOptions connectOptionsTemplate;
    Map<String, DBStatus> dbStatusCache = new HashMap<>();

    public PostgresBackedVhostResolver() {
        this(DataHub.getPreferredHub().getMain(), //
                DataHub.getPreferredHub().getTemplate());
    }

    public PostgresBackedVhostResolver(DataContext master, ConnectOptions connectOptionsTemplate) {
        if (master == null)
            throw new NullPointerException("master");
        if (connectOptionsTemplate == null)
            throw new NullPointerException("connectOptionsTemplate");
        this.master = master;
        this.connectOptionsTemplate = connectOptionsTemplate;
    }

    @Override
    public IVirtualHost get(String id) {
        return super.get(id);
    }

    @Override
    public synchronized IVirtualHost resolve(HttpServletRequest request) {
        IVirtualHost vhost = super.resolve(request);
        if (vhost == null)
            vhost = findAndAdd(request);
        return vhost;
    }

    public IVirtualHost findAndAdd(HttpServletRequest request) {
        String vhostName = getRequestName(request);
        IVirtualHost vhost = get(vhostName);
        if (vhost != null)
            return vhost;

        String databaseName = getDatabaseName(vhostName);
        boolean exists = checkDatabaseExists(databaseName);
        if (!exists)
            return null;

        MutableVirtualHost newVhost = create(vhostName, databaseName);
        // newVhost.addBinding(request.getServerName());
        add(newVhost);
        return newVhost;
    }

    public MutableVirtualHost create(String vhostName, String databaseName) {
        MutableVirtualHost newVhost = new MutableVirtualHost();
        newVhost.setName(vhostName);
        // newVhost.addBinding(vhostName + ".xxx");

        ConnectOptions opts = connectOptionsTemplate.clone();
        opts.setDatabase(databaseName);
        newVhost.setAttribute(ConnectOptions.ATTRIBUTE_KEY, opts);
        return newVhost;
    }

    protected String getDatabaseName(String vhostId) {
        return vhostId;
    }

    protected boolean checkDatabaseExists(String databaseName) {
        if (databaseName == null)
            throw new NullPointerException("databaseName");

        DBStatus dbStatus = dbStatusCache.get(databaseName);
        if (dbStatus == null) {
            dbStatus = getDatabaseStatus(databaseName);
            dbStatusCache.put(databaseName, dbStatus);
        }
        return dbStatus.exists;
    }

    DBStatus getDatabaseStatus(String databaseName) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DataSource dataSource = master.getDataSource();
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Failed to connect to master database at " + master.getOptions().getConnectionUrl(), e);

            // XXX security leaks..
            ConnectOptions options = master.getOptions();
            logger.error(" master options: " + options);

            throw new RuntimeException(e.getMessage(), e);
        }

        try {
            String sql = "select *, pg_encoding_to_char(encoding) encname from pg_database where datname=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, databaseName);

            rs = ps.executeQuery();

            if (!rs.next())
                return DBStatus.notExisted();

            String datname = rs.getString("datname");
            assert databaseName.equals(datname);

            return DBStatus.pg_database(rs);
        } catch (SQLException e) {
            logger.error("Failed to query master database: " + e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            close(rs);
            close(ps);
            close(connection);
        }
    }

    void close(AutoCloseable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (Exception e) {
                logger.error(e, "Can't close " + closeable);
            }
    }

    static PostgresBackedVhostResolver DEFAULT;

    public static synchronized PostgresBackedVhostResolver getProjectDefault() {
        if (DEFAULT == null)
            DEFAULT = new PostgresBackedVhostResolver();
        return DEFAULT;
    }

}
