package net.bodz.bas.vfs;

public class FileResolveOptions {

    private final boolean followLinks;

    public FileResolveOptions(boolean followLinks) {
        this.followLinks = followLinks;
    }

    public boolean isFollowLinks() {
        return followLinks;
    }

    public static final FileResolveOptions DEFAULT = new FileResolveOptions(true);
    public static final FileResolveOptions NO_FOLLOW_LINKS = new FileResolveOptions(false);

}
