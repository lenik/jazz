package net.bodz.bas.db.jdbc;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.StringId;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.xml.IXmlForm;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.fmt.xml.xq.QElementMap;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.meta.decl.NotNull;

public class ConnectOptions
        implements
            Serializable,
            Cloneable,
            IJsonForm,
            IXmlForm {

    private static final long serialVersionUID = 1L;

//    public static final String ATTRIBUTE_KEY = ConnectOptions.class.getName();

    private String id;

    private String sourceUri;
    private long lastModifiedTime;

    private DatabaseType type = DatabaseType.POSTGRESQL;
    private String _connectionUrl;
    private String hostName = "localhost";
    private int port;
    private File rootDir;
    private String database;
    private String userName;
    private String password;
    private Properties properties;

    private int poolSize = 10;

    public ConnectOptions(String id) {
        this.id = id;
        properties = new Properties();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(String sourceUri) {
        this.sourceUri = sourceUri;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    void touch() {
        lastModifiedTime = System.currentTimeMillis();
    }

    @Override
    public ConnectOptions clone() {
        ConnectOptions o = new ConnectOptions(id);
        o.sourceUri = sourceUri;
        o.lastModifiedTime = lastModifiedTime;
        o.type = type;
        o.hostName = hostName;
        o.port = port;
        o.rootDir = rootDir;
        o.database = database;
        o.userName = userName;
        o.password = password;
        if (properties != null)
            o.properties.putAll(properties);
        return o;
    }

    public DatabaseType getType() {
        return type;
    }

    public void setType(DatabaseType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
        touch();
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
        return _connectionUrl;
    }

    public void setUrl(String url) {
        this._connectionUrl = url;
        touch();
    }

    @Transient
    public synchronized String getServer() {
        if (port == 0)
            return hostName;
        else
            return hostName + ":" + port;
    }

    public synchronized void setServer(String server) {
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
        touch();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
        touch();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        touch();
    }

    /**
     * some databases like H2, HSQL use directory instead of database name.
     */
    public File getRootDir() {
        return rootDir;
    }

    public void setRootDir(File rootDir) {
        this.rootDir = rootDir;
        touch();
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
        touch();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        touch();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        touch();
    }

    @Transient
    public String getConnectionUrl() {
        String url = this._connectionUrl;
        if (url == null) {
            url = type.getConnectionUrl(this);
            this._connectionUrl = url;
        }
        return url;
    }

    public Properties getProperties() {
        return properties;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((database == null) ? 0 : database.hashCode());
        result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + port;
        result = prime * result + ((rootDir == null) ? 0 : rootDir.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((_connectionUrl == null) ? 0 : _connectionUrl.hashCode());
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
    public String toString() {
        return sourceUri + "\n" + _connectionUrl;
    }

    public static final String K_SOURCE_URI = "sourceUri";
    public static final String K_LAST_MODIFIED_TIME = "lastModifiedTime";
    public static final String K_TYPE = "type";
    public static final String K_URL = "url";
    public static final String K_HOSTNAME = "hostName";
    public static final String K_PORT = "port";
    public static final String K_ROOTDIR = "rootDir";
    public static final String K_DATABASE = "database";
    public static final String K_USERNAME = "userName";
    public static final String K_PASSWORD = "password";
    public static final String K_PROPERTIES = "properties";
    public static final String K_PROPERTY = "property";
    public static final String K_PROPERTY_NAME = "name";

    public static final String K_POOL_SIZE = "poolSize";

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        type = o.getVar(DatabaseType.class, K_TYPE);
        _connectionUrl = o.getString(K_URL, _connectionUrl);
        hostName = o.getString(K_HOSTNAME, hostName);
        port = o.getInt(K_PORT, port);
        rootDir = o.getFile(K_ROOTDIR, rootDir);
        database = o.getString(K_DATABASE, database);
        userName = o.getString(K_USERNAME, userName);
        password = o.getString(K_PASSWORD, password);

        JsonObject oProps = o.getJsonObject(K_PROPERTIES);
        if (oProps != null) {
            if (properties == null)
                properties = new Properties();
            for (String k : oProps.keySet()) {
                String v = oProps.getString(k);
                properties.setProperty(k, v);
            }
        }

        poolSize = o.getInt(K_POOL_SIZE, poolSize);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry(K_TYPE, type);
        out.entry(K_URL, _connectionUrl);
        out.entry(K_HOSTNAME, hostName);
        out.entry(K_PORT, port);
        out.entry(K_ROOTDIR, rootDir);
        out.entry(K_DATABASE, database);
        out.entry(K_USERNAME, userName);
        out.entry(K_PASSWORD, password);
        if (properties != null && ! properties.isEmpty()) {
            out.key(K_PROPERTIES);
            out.object();
            for (String name : properties.stringPropertyNames()) {
                String val = properties.getProperty(name);
                out.entry(name, val);
            }
            out.endObject();
        }
        out.entry(K_POOL_SIZE, poolSize);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        QElementMap map = (QElementMap) element.getChildrenMap();
        String typeName = map.getString(K_TYPE);
        type = typeName == null ? null : DatabaseType.meta.ofName(typeName);
        _connectionUrl = map.getString(K_URL);
        hostName = map.getString(K_HOSTNAME);
        port = map.getInt(K_PORT, 0);
        String rootDirPath = map.getString(K_ROOTDIR);
        rootDir = rootDirPath == null ? null : new File(rootDirPath);
        database = map.getString(K_DATABASE);
        userName = map.getString(K_USERNAME);
        password = map.getString(K_PASSWORD);

        IElement x_props = element.getChild(K_PROPERTIES);
        if (x_props != null) {
            if (properties == null)
                properties = new Properties();
            for (IElement x_prop : x_props.children()) {
                String k = x_prop.getTagName();
                String rename = x_prop.getAttribute(K_PROPERTY_NAME);
                if (rename != null)
                    k = rename;
                String v = x_prop.getTextContent();
                properties.setProperty(k, v);
            }
        }

        poolSize = map.getInt(K_POOL_SIZE, poolSize);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.element(K_TYPE, type);
        out.element(K_URL, _connectionUrl);
        out.element(K_HOSTNAME, hostName);
        out.element(K_PORT, port);
        out.element(K_ROOTDIR, rootDir);
        out.element(K_DATABASE, database);
        out.element(K_USERNAME, userName);
        out.element(K_PASSWORD, password);
        if (properties != null && ! properties.isEmpty()) {
            out.beginElement(K_PROPERTIES);
            for (String name : properties.stringPropertyNames()) {
                String val = properties.getProperty(name);
                if (StringId.isXmlTagName(name))
                    out.element(name, val);
                else {
                    out.beginElement(K_PROPERTY);
                    out.attribute(K_PROPERTY_NAME, name);
                    out.writeCharacters(val);
                    out.endElement();
                }
            }
            out.endElement();
            out.element(K_POOL_SIZE, poolSize);
        }
    }

}
