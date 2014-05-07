package net.bodz.swt.ui.style;

import java.io.Serializable;

import org.eclipse.swt.graphics.Pattern;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.ui.style.FillPatternType;
import net.bodz.bas.ui.style.IFillType;

public class SwtFillType
        implements IFillType, Serializable {

    private static final long serialVersionUID = 1L;

    private final Pattern pattern;
    private final boolean managed;

    public SwtFillType(Pattern pattern) {
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
        if (!(obj instanceof SwtFillType))
            return false;

        SwtFillType o = (SwtFillType) obj;
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

    /** ⇱ Implementaton Of {@link IFillType}. */
    /* _____________________________ */static section.iface __FILLTYPE__;

    @Override
    public FillPatternType getFillPatternType() {
        return FillPatternType.image;
    }

    @Override
    public String getFillPattern() {
        throw new NotImplementedException();
    }

}
