package net.bodz.bas.site.file;

import java.nio.file.Path;

public class ArchiveResult {

    Path src;
    Path archived;
    String sha1;

    public ArchiveResult(Path src, Path archived, String sha1) {
        this.src = src;
        this.archived = archived;
        this.sha1 = sha1;
    }

}
