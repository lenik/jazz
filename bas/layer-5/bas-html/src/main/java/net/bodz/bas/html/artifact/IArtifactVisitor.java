package net.bodz.bas.html.artifact;

public interface IArtifactVisitor {

    void begin(IArtifactDependent artifact);

    void end(IArtifactDependent artifact);

    void artifact(IArtifact artifact);

    void dependency(IArtifactDependency dependency);

}
