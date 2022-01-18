package net.bodz.bas.db.jdbc;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.Transient;

public class ConnectOptions
        implements
            Serializable,
            Cloneable,
            IJsonForm {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_KEY = ConnectOptions.class.getName();

    private DatabaseType type = DatabaseType.POSTGRESQL;
    private String url;
    private String hostName = "localhost";
    private int port;
    private File rootDir;
    private String database;
    private String userName;
    private String password;
    private Properties info;

    public ConnectOptions() {
        info = new Properties();
    }

    @Override
    public ConnectOptions clone() {
        ConnectOptions o = new ConnectOptions();
        o.type = type;
        o.hostName = hostName;
        o.port = port;
        o.rootDir = rootDir;
        o.database = database;
        o.userName = userName;
        o.password = password;
        return o;
    }

    public DatabaseType getType() {
        return type;
    }

    public void setType(DatabaseType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    public void setType(String typeStr) {
        DatabaseType type = DatabaseType.meta.ofName(typeStr);
        if (type == null)
            throw new IllegalArgumentException("Bad type string: " + typeStr);
        setType(type);
    }

    /**
     * Connection URL override.
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Transient
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

    /**
     * some databases like H2, HSQL use directory instead of database name.
     */
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

    @Transient
    public String getConnectionUrl() {
        String url = this.url;
        if (url == null)
            url = type.getConnectionUrl(this);
        return url;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((database == null) ? 0 : database.hashCode());
        result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + port;
        result = prime * result + ((rootDir == null) ? 0 : rootDir.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConnectOptions o = (ConnectOptions) obj;
        if (Nullables.notEquals(database, o.database))
            return false;
        return true;
    }

    public DataContext newDataContext() {
        return new DataContext(this);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        type = o.getVar(DatabaseType.class, "type");
        url = o.getString("url", url);
        hostName = o.getString("hostName", hostName);
        port = o.getInt("port", port);
        rootDir = o.getFile("rootDir", rootDir);
        database = o.getString("database", database);
        userName = o.getString("userName", userName);
        password = o.getString("password", password);

        JsonObject oInfo = o.getJsonObject("info");
        if (oInfo != null) {
            if (info == null)
                info = new Properties();
            for (String k : oInfo.keySet()) {
                String v = oInfo.getString(k);
                info.setProperty(k, v);
            }
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        out.entry("type", type);
        out.entry("url", url);
        out.entry("hostName", hostName);
        out.entry("port", port);
        out.entry("rootDir", rootDir);
        out.entry("database", database);
        out.entry("userName", userName);
        out.entry("password", password);
        if (info != null) {
            out.key("info");
            out.object();
            for (String name : info.stringPropertyNames()) {
                String val = info.getProperty(name);
                out.entry(name, val);
            }
            out.endObject();
        }
    }

}
