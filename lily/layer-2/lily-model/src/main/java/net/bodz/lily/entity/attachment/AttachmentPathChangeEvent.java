package net.bodz.lily.entity.attachment;

import java.util.EventObject;

import net.bodz.lily.storage.IVolume;

public class AttachmentPathChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    IVolume oldVolume;
    String oldPath;

    IVolume newVolume;
    String newPath;

    public AttachmentPathChangeEvent(Object source) {
        super(source);
    }

    public IVolume getOldVolume() {
        return oldVolume;
    }

    public void setOldVolume(IVolume oldVolume) {
        this.oldVolume = oldVolume;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public IVolume getNewVolume() {
        return newVolume;
    }

    public void setNewVolume(IVolume newVolume) {
        this.newVolume = newVolume;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

}
