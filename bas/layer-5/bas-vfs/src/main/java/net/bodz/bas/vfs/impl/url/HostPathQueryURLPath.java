package net.bodz.bas.vfs.impl.url;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.vfs.path.PathFormat;

public class HostPathQueryURLPath
        extends URLPath {

    private static final long serialVersionUID = 1L;

    private final String user;
    private final String password;
    private final String host;
    private final int port;

    // private final String drive;
    private final String realPath;

    // private transient String userInfo;
    // private transient String authority;

    /**
     * @param userInfo
     *            User name and password. Part of the device spec.
     * @param authority
     *            Authority name (host and its port). Part of the device spec.
     * @param drive
     *            Drive name. Part of the device spec.
     */
    public HostPathQueryURLPath(String scheme, String userInfo, String authority, String drive, String localPath) {
        this(scheme, //
                StringPart.before(userInfo, ':', userInfo), StringPart.after(userInfo, ':'), //
                StringPart.before(authority, ':', authority), _parsePort(StringPart.after(authority, ':')), //
                localPath);
    }

    static int _parsePort(String portSpec) {
        if (portSpec == null)
            return -1;
        else
            return Integer.parseInt(portSpec);
    }

    public HostPathQueryURLPath(String scheme, String user, String password, String host, int port,
            String[] localEntries) {
        super(scheme, localEntries);
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;

        String localPath = getLocalPath();
        int quest = localPath.lastIndexOf('?');
        if (quest != -1) {
            query = localPath.substring(quest + 1);
            realPath = localPath.substring(0, quest);
        } else {
            realPath = localPath;
        }
    }

    public HostPathQueryURLPath(String scheme, String user, String password, String host, int port, String localPath) {
        super(scheme, localPath);
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;

        int quest = localPath.lastIndexOf('?');
        if (quest != -1) {
            query = localPath.substring(quest + 1);
            realPath = localPath.substring(0, quest);
        } else {
            realPath = localPath;
        }
    }

    @Override
    protected HostPathQueryURLPath createLocal(String localPath) {
        return new HostPathQueryURLPath(scheme, user, password, host, port, localPath);
    }

    @Override
    protected HostPathQueryURLPath createLocal(String[] entries) {
        return new HostPathQueryURLPath(scheme, user, password, host, port, entries);
    }

    public String getUserInfo() {
        if (user == null)
            return null;
        else if (password == null)
            return user;
        else
            return user + ":" + password;
    }

    public String getAuthority() {
        if (host == null)
            return null;
        else if (port == -1)
            return host;
        else
            return host + ":" + port;
    }

    public String getRealPath() {
        return realPath;
    }

    public String getRealBaseName() {
        int slash = realPath.lastIndexOf('/');
        if (slash != -1)
            return realPath.substring(slash + 1);
        else
            return realPath;
    }

    @Override
    public String getDeviceSpec() {
        String userInfo = getUserInfo();
        String authority = getAuthority();

        if (userInfo == null && authority == null)
            return null;

        StringBuilder buf = new StringBuilder();
        buf.append("//");
        if (userInfo != null) {
            buf.append(userInfo);
            buf.append("@");
        }
        if (authority != null)
            buf.append(authority);

        return buf.toString();
    }

    @Override
    protected void format(StringBuilder result, PathFormat format) {
        boolean anyDeviceSpec = false;
        if (!format.isDisplaySafe()) {
            if (user != null) {
                result.append(user); // encode?
                if (password != null) {
                    result.append(':');
                    result.append(password); // encode?
                }
                result.append('@');
                anyDeviceSpec = true;
            }
        }

        if (host != null) {
            result.append(host);
            if (port != -1) {
                result.append(':');
                result.append(port);
            }
            anyDeviceSpec = true;
        }

        if (anyDeviceSpec)
            result.append(getDeviceSpecSeparator());

        String local = formatLocal(format);
        result.append(local);

        if (format.isRange()) {
            if (fragmentId != null) {
                result.append('#');
                result.append(fragmentId);
            }
        }
    }

    @Override
    protected String formatLocal(PathFormat format) {
        String result;
        if (format.isQuery()) {
            result = getLocalPath();
        } else {
            result = realPath;
        }
        if (format.getEncodeOptions() != 0) {
            // format.getEncodeCharset();
            // ...
        }
        return result;
    }

}
