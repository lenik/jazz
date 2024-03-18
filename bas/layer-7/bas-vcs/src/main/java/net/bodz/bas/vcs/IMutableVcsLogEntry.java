package net.bodz.bas.vcs;

import java.time.ZonedDateTime;

public interface IMutableVcsLogEntry
        extends
            IVcsLogEntry {

    void setVersion(String version);

    void setTreeHash(String treeHash);

    void setAuthorName(String authorName);

    void setAuthorEmail(String authorEmail);

    void setAuthorDate(ZonedDateTime authorDate);

    void setCommitterName(String committerName);

    void setCommitterEmail(String committerEmail);

    void setCommitDate(ZonedDateTime commitDate);

    void setSubject(String subject);

    void setBody(String body);

}