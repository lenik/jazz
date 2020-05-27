package net.bodz.bas.html.artifact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.err.ConflictedVersionException;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.rtx.AbstractQueryable;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.std.rfc.mime.ContentType;

public class Artifact
        extends AbstractQueryable
        implements Serializable, IMutableArtifact {

    private static final long serialVersionUID = 1L;

    private String name;
    private IVersion version;
    private ArtifactType type;
    private ContentType contentType;
    private IAnchor anchor;
    private IArtifact parent;
    private List<IArtifact> children = new ArrayList<IArtifact>();

    private MutableArtifactDependent dependent = new MutableArtifactDependent();

    public Artifact(String name) {
        this(name, null);
    }

    public Artifact(String name, String versionStr) {
        this.name = name;
        if (versionStr != null)
            this.version = IVersion.fn.parse(versionStr);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IVersion getVersion() {
        return version;
    }

    @Override
    public void setVersion(IVersion version) {
        this.version = version;
    }

    public final void setVersionFromString(String versionStr) {
        IVersion version = IVersion.fn.parse(versionStr);
        setVersion(version);
    }

    @Override
    public ArtifactType getType() {
        return type;
    }

    @Override
    public void setType(ArtifactType type) {
        this.type = type;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public IAnchor getAnchor() {
        return anchor;
    }

    public void setAnchor(IAnchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public IArtifact getParent() {
        return parent;
    }

    public void setParent(IArtifact parent) {
        this.parent = parent;
    }

    @Override
    public List<IArtifact> getChildren() {
        return children;
    }

    public void addChild(IArtifact artifact) {
        children.add(artifact);
    }

    public void removeChild(IArtifact artifact) {
        children.remove(artifact);
    }

    @Override
    public void accept(IArtifactVisitor visitor) {
        visitor.begin(this);

        for (IArtifactDependency dependency : getDependencies())
            visitor.dependency(dependency);

        for (IArtifact child : children) {
            child.accept(visitor);
            visitor.artifact(child);
        }

        visitor.end(this);
    }

    /** ⇱ Implementation Of {@link IArtifactDependent}. */
    /* _____________________________ */static section.iface __DEPENDENT__;

    @Override
    public Collection<? extends IArtifactDependency> getDependencies() {
        return dependent.getDependencies();
    }

    @Override
    public Collection<? extends IArtifactDependency> getDependencies(ArtifactType type) {
        return dependent.getDependencies(type);
    }

    @Override
    public final MutableArtifactDependency addDependency(String name) {
        return dependent.addDependency(name);
    }

    @Override
    public final MutableArtifactDependency addDependency(String name, ArtifactType type) {
        return dependent.addDependency(name, type);
    }

    @Override
    public final MutableArtifactDependency addDependency(String name, ArtifactType type, String minVersionStr,
            String maxVersionStr)
            throws ConflictedVersionException {
        return dependent.addDependency(name, type, minVersionStr, maxVersionStr);
    }

    @Override
    public MutableArtifactDependency addDependency(IArtifact artifact) {
        return dependent.addDependency(artifact);
    }

    @Override
    public final void addDependency(IArtifactDependency dependency)
            throws ConflictedVersionException {
        dependent.addDependency(dependency);
    }

    @Override
    public final void removeDependency(String name) {
        dependent.removeDependency(name);
    }

    public Artifact dependsOn(IArtifact artifact) {
        addDependency(artifact);
        return this;
    }

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
