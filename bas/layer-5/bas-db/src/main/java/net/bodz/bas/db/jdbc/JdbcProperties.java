package net.bodz.bas.db.jdbc;

import java.io.File;
import java.io.Serializable;

import net.bodz.bas.potato.ref.PropertyRefMap;
import net.bodz.bas.potato.ref.ValueEntry;

public class JdbcProperties
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_KEY = JdbcProperties.class.getName();

    Dialect dialect = Dialect.PostgreSQL;
    String hostName = "localhost";
    int port;
    File rootDir;
    String database;
    String userName;
    String password;

    public JdbcProperties() {
        // rootDir = new File("/var/data");
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public String getServer() {
        if (port == 0)
            return hostName;
        else
            return hostName + ":" + port;
    }

    public void setServer(String server) {
        if (server == null) {
            hostName = null;
            port = 0;
        } else {
            int colon = server.lastIndexOf(':');
            if (colon == -1) {
                hostName = server;
                port = 0;
            } else {
                int portNum = Integer.parseInt(server.substring(colon + 1));
                hostName = server.substring(0, colon);
                port = portNum;
            }
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public File getRootDir() {
        return rootDir;
    }

    public void setRootDir(File rootDir) {
        this.rootDir = rootDir;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        JdbcProperties inst = new JdbcProperties();
        inst.hostName = "foo";
        inst.database = "mas";

        PropertyRefMap refMap = new PropertyRefMap(ValueEntry.wrap(inst), null);
        String url = Dialect.MySQL.getConnectionUrl(refMap);
        System.out.println(url);
    }

}
