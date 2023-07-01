package net.bodz.lily.storage;

import java.io.FileNotFoundException;
import java.io.IOException;

import net.bodz.bas.t.file.IPathFields;
import net.bodz.bas.t.file.BottomUpPathFields;
import net.bodz.bas.t.tuple.Split;

public class DefaultVolumeFile
        extends BottomUpPathFields
        implements
            IVolumeItem {

    IVolume volume;
    Boolean symLinkToSha1;
    Long size;
    String sha1;

//    public DefaultVolumeFile(IVolume volume) {
//        if (volume == null)
//            throw new NullPointerException("volume");
//        this.volume = volume;
//    }

    public DefaultVolumeFile(IVolume volume, IPathFields pathFields) {
        super(pathFields);
        if (volume == null)
            throw new NullPointerException("volume");
        this.volume = volume;
    }

    @Override
    public IVolume getVolume() {
        return volume;
    }

    @Override
    public void setVolume(IVolume volume) {
        if (volume == null)
            throw new NullPointerException("volume");
        this.volume = volume;
    }

    @Override
    public boolean exists() {
        return volume.exists(getPath());
    }

    @Override
    public synchronized Boolean isSymLinkToSha1()
            throws IOException {
        if (symLinkToSha1 == null) {
            if (volume == null)
                return null;
            if (!volume.isSymLink(getPath()))
                return false;
            String target = volume.getSymLinkTarget(getPath());
            String name = Split.nameExtension(target).a;
            String sha1 = getSHA1();
            if (name == null || sha1 == null)
                return false;
            symLinkToSha1 = name.equalsIgnoreCase(sha1);
        }
        return symLinkToSha1;
    }

    @Override
    public long getSize()
            throws FileNotFoundException {
        if (size == null)
            if (volume == null)
                return 0;
            else
                size = volume.getSize(getPath());
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public synchronized String getSHA1()
            throws IOException {
        if (sha1 == null)
            sha1 = volume.getSHA1(getPath());
        return sha1;
    }

    public void setSHA1(String sha1) {
        this.sha1 = sha1;
    }

}
