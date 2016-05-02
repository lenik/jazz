package net.bodz.bas.html.artifact;

import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IMutableArtifact
        extends IArtifact, IMutableArtifactDependent {

    void setName(String name);

    void setVersion(IVersion version);

    void setType(ArtifactType type);

    void setContentType(ContentType contentType);

}
