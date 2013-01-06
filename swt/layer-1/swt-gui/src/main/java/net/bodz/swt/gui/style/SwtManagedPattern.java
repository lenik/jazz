package net.bodz.swt.gui.style;

import java.io.Serializable;

import org.eclipse.swt.graphics.Pattern;

import net.bodz.bas.gui.style.IFillType;

public class SwtManagedPattern
        implements IFillType, Serializable {

    private static final long serialVersionUID = 1L;

    private final Pattern pattern;
    private final boolean managed;

    public SwtManagedPattern(Pattern pattern) {
        if (pattern == null)
            throw new NullPointerException("pattern");
        this.pattern = pattern;
        this.managed = false;
    }

    public boolean isDisposed() {
        return pattern.isDisposed();
    }

    public void dispose() {
        if (managed)
            pattern.dispose();
    }

    public Pattern getSwtPattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SwtManagedPattern))
            return false;

        SwtManagedPattern o = (SwtManagedPattern) obj;
        if (managed != o.managed)
            return false;
        if (!pattern.equals(o.pattern))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x88c44403;
        if (managed)
            hash += 0x6cf049df;
        hash += pattern.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return pattern.toString();
    }

}
