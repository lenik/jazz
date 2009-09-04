package net.bodz.swt.gui.pfl;

public abstract class RewriteRule {

    public static final int LAST        = 1;
    public static final int RESTART     = 2;

    public static final int IGNORE_CASE = 0x10;
    public static final int ANYWHERE    = 0x100;
    public static final int REPLACE_ALL = 0x200;

    protected final int     flags;

    public RewriteRule(int flags) {
        this.flags = flags;
        assert !(isWhole() && isReplaceAll());
    }

    public int getFlags() {
        return flags;
    }

    public boolean isLast() {
        return (flags & LAST) != 0;
    }

    public boolean isRestart() {
        return (flags & RESTART) != 0;
    }

    protected boolean isIgnoreCase() {
        return (flags & IGNORE_CASE) != 0;
    }

    protected boolean isAnywhere() {
        return (flags & ANYWHERE) != 0;
    }

    protected boolean isWhole() {
        return (flags & ANYWHERE) == 0;
    }

    protected boolean isReplaceAll() {
        return (flags & REPLACE_ALL) != 0;
    }

    public abstract boolean matches(String text);

    public abstract String rewrite(String text);

}
