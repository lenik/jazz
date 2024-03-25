package net.bodz.bas.fmt.json;

public class PrefixPathBuilder {

    private final StringBuilder buf;
    private int prefixLength;
    private boolean none;

    public PrefixPathBuilder(String prefix) {
        if (prefix != null) {
            this.buf = new StringBuilder(prefix);
            this.prefixLength = prefix.length();
        } else {
            this.buf = new StringBuilder();
            this.prefixLength = 0;
            this.none = true;
        }
    }

    public boolean isNone() {
        return none;
    }

    public String getPath() {
        if (none)
            return null;
        else
            return buf.substring(0, prefixLength);
    }

    void checkName(String name) {
        if (name.contains("/"))
            throw new IllegalArgumentException("invalid name: separator(/) is not allowed in the name.");
    }

    public void enter(String name) {
        checkName(name);
        reset();
        if (none)
            none = false;
        else
            buf.append('/');
        buf.append(name);
        set();
        onPathChange();
    }

    public void enter(long index) {
        reset();
        if (none)
            none = false;
        else {
            if (prefixLength == 0)
                buf.append('/');
        }
        buf.append('[');
        buf.append(index);
        buf.append(']');
        set();
        onPathChange();
    }

    public boolean canLeave() {
        return ! none;
    }

    public void leave() {
        if (none)
            throw new IllegalStateException("nowhere to leave");
        if (prefixLength == 0)
            none = true;
        else {
            char lastChar = buf.charAt(prefixLength - 1);
            if (lastChar == ']') {
                int leftBracket = buf.lastIndexOf("[", prefixLength - 2);
                if (leftBracket == -1)
                    throw new IllegalStateException("unmatched bracket: " + getPath());
                prefixLength = leftBracket;
                if (prefixLength == 1 && buf.charAt(0) == '/')
                    prefixLength = 0;
            } else {
                int lastSlash = buf.lastIndexOf("/", prefixLength - 1);
                if (lastSlash == -1) {
                    prefixLength = 0;
                    none = true;
                } else {
                    prefixLength = lastSlash;
                }
            }
        }
        onPathChange();
    }

    protected void onPathChange() {
    }

    void reset() {
        buf.setLength(prefixLength);
    }

    void set() {
        prefixLength = buf.length();
    }

    public synchronized String childPath(String name) {
        checkName(name);
        if (none)
            return name;
        reset();
        buf.append('/');
        buf.append(name);
        return buf.toString();
    }

    public synchronized String childPath(long index) {
        reset();
        buf.append('[');
        buf.append(index);
        buf.append(']');
        return buf.toString();
    }

    @Override
    public String toString() {
        String path = getPath();
        if (path == null)
            return "(none)";
        else
            return path;
    }

    public static void main(String[] args) {
        PrefixPathBuilder b = new PrefixPathBuilder("") {
            @Override
            protected void onPathChange() {
                System.out.println("Path: " + getPath());
            }
        };
        System.out.println("init: " + b.getPath());
        b.enter(1);
        b.enter(2);
        b.enter("bar");
        b.enter("");
//        b.enter("cat");
//        b.enter("");
        while (! b.isNone())
            b.leave();
    }

}
