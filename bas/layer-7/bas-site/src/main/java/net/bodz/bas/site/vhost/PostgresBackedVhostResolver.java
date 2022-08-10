package net.bodz.bas.site.vhost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public abstract class PostgresBackedVhostResolver
        extends MutableVirtualHostResolver {

    static final Logger logger = LoggerFactory.getLogger(PostgresBackedVhostResolver.class);

    private DataContext master;
    private ConnectOptions connectOptionsTemplate;

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
        if (vhost == null) {
            vhost = findAndAdd(request);
            if (vhost == null)
                vhost = getDefault();
        }
        return vhost;
    }

    public IVirtualHost findAndAdd(HttpServletRequest request) {
        String vhostName = getHostName(request, false);
        IVirtualHost vhost = get(vhostName);
        if (vhost != null)
            return vhost;

        String databaseName = getDatabaseName(vhostName);
        if (!checkDatabaseExists(databaseName))
            return null;

        MutableVirtualHost newVhost = create(vhostName, databaseName);
        newVhost.addHostSpecifier(request.getServerName());
        add(newVhost);
        return newVhost;
    }

    public MutableVirtualHost create(String vhostName, String databaseName) {
        MutableVirtualHost newVhost = new MutableVirtualHost();
        newVhost.setName(vhostName);
        newVhost.addHostSpecifier(vhostName + ".xxx");

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
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = master.getDataSource().getConnection();

            String sql = "select * from pg_database where datname=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, databaseName);

            rs = ps.executeQuery();

            if (!rs.next())
                return false;

            String datname = rs.getString("datname");
            assert databaseName.equals(datname);

            return true;
        } catch (SQLException e) {
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

}
