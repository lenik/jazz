package net.bodz.bas.db.jdbc;

import java.io.File;
import java.io.Serializable;

public class DataSourceArguments
        implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_KEY = DataSourceArguments.class.getName();

    DataSourceType type = DataSourceType.PostgreSQL;
    String hostName = "localhost";
    int port;
    File rootDir;
    String database;
    String userName;
    String password;

    public DataSourceArguments() {
    }

    public DataSourceArguments clone() {
        DataSourceArguments o = new DataSourceArguments();
        o.type = type;
        o.hostName = hostName;
        o.port = port;
        o.rootDir = rootDir;
        o.database = database;
        o.userName = userName;
        o.password = password;
        return o;
    }

    public DataSourceType getType() {
        return type;
    }

    public void setType(DataSourceType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
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

    public String getConnectionUrl() {
        String url = type.getConnectionUrl(this);
        return url;
    }

}