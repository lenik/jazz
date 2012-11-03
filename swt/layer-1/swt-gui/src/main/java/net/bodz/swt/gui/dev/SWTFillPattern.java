package net.bodz.swt.gui.dev;

import java.io.Serializable;

import org.eclipse.swt.graphics.Pattern;

import net.bodz.bas.gui.spec0.IFillPattern;

public class SWTFillPattern
        implements IFillPattern, Serializable {

    private static final long serialVersionUID = 1L;

    public Pattern pattern;

    public SWTFillPattern(Pattern pattern) {
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

        Pattern pat;
        if (object instanceof Pattern)
            pat = (Pattern) object;
        else if (object instanceof SWTFillPattern)
            pat = ((SWTFillPattern) object).pattern;
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
