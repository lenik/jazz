package net.bodz.bas.site.file;

import net.bodz.bas.meta.res.HaveAttachments;

public class AttachmentSettings {

    public String dirNaming;
    public String uploadLocation;
    public long maxSize;
    public String sharedDict;

    public AttachmentSettings(HaveAttachments a, String fileClass) {
        dirNaming = a.dirNaming();
        if (dirNaming.isEmpty())
            dirNaming = null;

        uploadLocation = a.uploadLocation();
        if (uploadLocation.isEmpty())
            uploadLocation = fileClass;

        maxSize = a.maxSize();

        sharedDict = a.sharedDict();
        if (sharedDict.isEmpty())
            sharedDict = null;
    }

    public static AttachmentSettings parse(HaveAttachments a, String fileClass) {
        return new AttachmentSettings(a, fileClass);
    }

}
