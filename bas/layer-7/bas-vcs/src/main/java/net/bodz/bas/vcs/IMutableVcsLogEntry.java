package net.bodz.bas.vcs;

import java.util.Calendar;

public interface IMutableVcsLogEntry
        extends IVcsLogEntry {

    void setVersion(String version);

    void setTreeHash(String treeHash);

    void setAuthorName(String authorName);

    void setAuthorEmail(String authorEmail);

    void setAuthorDate(Calendar authorDate);

    void setCommitterName(String committerName);

    void setCommitterEmail(String committerEmail);

    void setCommitDate(Calendar commitDate);

    void setSubject(String subject);

    void setBody(String body);

}