package net.bodz.swt.draw.dev.swt;

import net.bodz.swt.draw.dev.Pattern;

public class SWTPattern
        extends Pattern {

    static final long serialVersionUID = -3052298732340011339L;

    public org.eclipse.swt.graphics.Pattern pattern;

    public SWTPattern(org.eclipse.swt.graphics.Pattern pattern) {
        assert pattern != null;
        this.pattern = pattern;
    }

    public void dispose() {
        pattern.dispose();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;

        org.eclipse.swt.graphics.Pattern pat;
        if (object instanceof org.eclipse.swt.graphics.Pattern)
            pat = (org.eclipse.swt.graphics.Pattern) object;
        else if (object instanceof SWTPattern)
            pat = ((SWTPattern) object).pattern;
        else
            return false;

        return pattern.equals(pat);
    }

    @Override
    public int hashCode() {
        return pattern.hashCode();
    }

    public boolean isDisposed() {
        return pattern.isDisposed();
    }

    @Override
    public String toString() {
        return pattern.toString();
    }

}
