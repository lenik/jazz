package net.bodz.bas.vfs.path;

import java.io.Serializable;

public class PathCanonicalizeOptions
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean followSymlinks;
    private boolean symlinkMustExist;

    // private boolean mustExist;
    // private boolean translateCase;

    public boolean isFollowSymlinks() {
        return followSymlinks;
    }

    public void setFollowSymlinks(boolean followSymlinks) {
        this.followSymlinks = followSymlinks;
    }

    public boolean isSymlinkMustExist() {
        return symlinkMustExist;
    }

    public void setSymlinkMustExist(boolean symlinkMustExist) {
        this.symlinkMustExist = symlinkMustExist;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PathCanonicalizeOptions))
            return false;
        PathCanonicalizeOptions o = (PathCanonicalizeOptions) obj;
        if (followSymlinks != o.followSymlinks)
            return false;
        if (symlinkMustExist != o.symlinkMustExist)
            return false;
        return true;
    }

}
