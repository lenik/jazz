package net.bodz.bas.vcs;

import java.io.IOException;

public class VcsLogBuilder {

    private IVcsWorkingCopy workingCopy;
    private String name;
    private VcsLogOptions options;

    VcsLogBuilder(IVcsWorkingCopy workingCopy, String name) {
        if (workingCopy == null)
            throw new NullPointerException("workingCopy");
        if (name == null)
            throw new NullPointerException("name");
        this.workingCopy = workingCopy;
        this.name = name;
        this.options = new VcsLogOptions();
    }

    public IVcsLogEntry getFromEntry() {
        return options.fromEntry;
    }

    public VcsLogBuilder setFromEntry(IVcsLogEntry fromEntry) {
        options.fromEntry = fromEntry;
        return this;
    }

    public boolean isFollowRenames() {
        return options.followRenames;
    }

    public VcsLogBuilder setFollowRenames(boolean followRenames) {
        options.followRenames = followRenames;
        return this;
    }

    public int getMaxEntries() {
        return options.maxEntries;
    }

    public VcsLogBuilder setMaxEntries(int maxEntries) {
        options.maxEntries = maxEntries;
        return this;
    }

    public Iterable<IVcsLogEntry> list()
            throws IOException, InterruptedException {
        return workingCopy.log(name, options);
    }

}
