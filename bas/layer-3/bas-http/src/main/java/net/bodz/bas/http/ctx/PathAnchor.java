package net.bodz.bas.http.ctx;

public class PathAnchor
        extends StructAnchor {

    private String path;
    private boolean directory;

    public PathAnchor(String path) {
        if (path == null)
            throw new NullPointerException("href");
        if (!path.startsWith("/"))
            throw new IllegalArgumentException("Not absolute path: " + path);

        this.path = path;
        this.directory = path.endsWith("/");
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public String absoluteHref() {
        return path;
    }

    @Override
    protected String getLocalPath() {
        return path;
    }

    @Override
    protected IAnchor create(String localPath) {
        return new PathAnchor(localPath);
    }

}