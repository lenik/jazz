package net.bodz.bas.vcs;

import java.util.Calendar;
import java.util.Collection;

import net.bodz.bas.t.pojo.Pair;

public interface IVcsLogEntry {

    /**
     * Can be:
     * <ul>
     * <li>commit-hash for Git.
     * <li>repository-version for SVN.
     * <li>file-version for CVS.
     * </ul>
     */
    String getVersion();

    /**
     * Only for Git.
     */
    String getTreeHash();

    String getAuthorName();

    String getAuthorEmail();

    Calendar getAuthorDate();

    String getCommitterName();

    String getCommitterEmail();

    Calendar getCommitDate();

    String getSubject();

    String getBody();

    Collection<IVcsLogEntry> getParents();

    Collection<IFileChangement> getChanges();

    class fn {

        public static Pair<Integer, Integer> getChangedLineCount(IVcsLogEntry entry) {
            int addCount = 0;
            int removeCount = 0;
            for (IFileChangement ch : entry.getChanges()) {
                addCount += ch.getAddedLines();
                removeCount += ch.getRemovedLines();
            }
            return Pair.<Integer, Integer> of(addCount, removeCount);
        }

    }

}
