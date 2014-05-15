package net.bodz.bas.html.artifact;

import net.bodz.bas.rtx.AbstractQueryable;

public abstract class AbstractArtifact
        extends AbstractQueryable
        implements IArtifact {

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getName());
        buf.append(" (");
        buf.append(getContentType());
        buf.append(")");
        return buf.toString();
    }

}
