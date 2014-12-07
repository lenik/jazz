package net.bodz.bas.html.artifact;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IArtifact
        extends IQueryable, IMutableArtifactDependent {

    String getName();

    void setName(String name);

    IVersion getVersion();

    void setVersion(IVersion version);

    ArtifactType getType();

    void setType(ArtifactType type);

    ContentType getContentType();

    void setContentType(ContentType contentType);

    IAnchor getAnchor();

}
