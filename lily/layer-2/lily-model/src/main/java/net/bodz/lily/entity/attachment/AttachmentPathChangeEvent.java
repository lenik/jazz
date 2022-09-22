package net.bodz.lily.entity.attachment;

import java.util.EventObject;

public class AttachmentPathChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    IAttachmentVolume oldVolume;
    String oldPath;

    IAttachmentVolume newVolume;
    String newPath;

    public AttachmentPathChangeEvent(Object source) {
        super(source);
    }

    public IAttachmentVolume getOldVolume() {
        return oldVolume;
    }

    public void setOldVolume(IAttachmentVolume oldVolume) {
        this.oldVolume = oldVolume;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public IAttachmentVolume getNewVolume() {
        return newVolume;
    }

    public void setNewVolume(IAttachmentVolume newVolume) {
        this.newVolume = newVolume;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

}
