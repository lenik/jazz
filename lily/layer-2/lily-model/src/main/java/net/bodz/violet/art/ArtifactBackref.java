package net.bodz.violet.art;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.BackrefRecord;

@IdType(Integer.class)
public class ArtifactBackref
        extends BackrefRecord {

    private static final long serialVersionUID = 1L;

    Artifact artifact;

    public ArtifactBackref() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
