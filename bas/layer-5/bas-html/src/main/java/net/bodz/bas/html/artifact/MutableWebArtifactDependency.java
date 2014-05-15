package net.bodz.bas.html.artifact;

import net.bodz.bas.meta.build.VersionRange;

public class MutableWebArtifactDependency
        extends MutableArtifactDependency {

    String candidateUrl;

    public MutableWebArtifactDependency(String name, String type, VersionRange versionRange) {
        super(name, type, versionRange);
    }

    public String getCandidateUrl() {
        return candidateUrl;
    }

    public void setCandidateUrl(String candidateUrl) {
        this.candidateUrl = candidateUrl;
    }

}
