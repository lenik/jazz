package net.bodz.bas.html.artifact;

import java.util.List;

import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IArtifact
        extends IQueryable, IArtifactDependent {

    String getName();

    IVersion getVersion();

    ArtifactType getType();

    ContentType getContentType();

    IAnchor getAnchor();

    IArtifact getParent();

    List<IArtifact> getChildren();

}
