package net.bodz.bas.vfs.impl.zip;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.NotLinkException;
import java.util.Map;

import net.bodz.bas.ar.zip.ZipArchiver;
import net.bodz.bas.ar.zip.ZipEntry;
import net.bodz.bas.ar.zip.ZipUnarchiver;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IRemovableMedia;
import net.bodz.bas.vfs.path.IPath;

public class ZipVfsDevice
        extends AbstractVfsDevice
        implements IRemovableMedia {

    private IFile deviceFile;
    private IByteIOS deviceIos;
    private ZipArchiver zipArchiver;
    private ZipUnarchiver zipUnarchiver;

    private ZipEntryFile rootFile;
    private Map<String, ZipEntryFile> fileMap;

    private boolean allEntriesLoaded;

    /**
     * @param zipName
     *            Drive name. <code>null</code> for linux root, or [a-z] for DOS drives.
     */
    public ZipVfsDevice(ZipVfsDriver driver, IFile deviceFile, String zipName) {
        super(driver, driver.protocol, zipName);
        this.deviceFile = deviceFile;
    }

    public IByteIOS ios()
            throws IOException {
        if (deviceIos == null)
            this.deviceIos = deviceFile.getResource().newByteIOS();
        return deviceIos;
    }

    public ZipArchiver archiver()
            throws IOException {
        if (zipArchiver == null) {
            OutputStream out = deviceFile.getOutputTarget().newOutputStream();
            zipArchiver = new ZipArchiver(out);
        }
        return zipArchiver;
    }

    public ZipUnarchiver unarchiver()
            throws IOException {
        if (zipUnarchiver == null)
            zipUnarchiver = new ZipUnarchiver(ios());
        return zipUnarchiver;
    }

    @Override
    public IFile getDeviceFile() {
        return deviceFile;
    }

    @Override
    public ZipEntryFile getRootFile() {
        return rootFile;
    }

    @Override
    public ZipEntryPath parseLocalPath(String localPath) {
        return new ZipEntryPath(getProtocol(), getDeviceSpec(), localPath);
    }

    @Override
    public ZipEntryFile _resolveNoRec(IPath _path)
            throws FileResolveException {
        if (_path == null)
            throw new NullPointerException("_path");
        return _resolveNoRec(_path.getLocalPath());
    }

    @Override
    public ZipEntryFile _resolveNoRec(String localPath) {
        if (Nullables.isEmpty(localPath))
            throw new IllegalArgumentException("localPath is empty.");

        while (localPath.startsWith("/"))
            localPath = localPath.substring(1);

        if (localPath.isEmpty())
            return rootFile;

        ZipEntryFile file = fileMap.get(localPath);
        if (file == null) {
            ZipEntry entry;
            try {
                entry = (ZipEntry) zipUnarchiver.getEntry(localPath);
            } catch (IOException e) {
                throw new FileResolveException(e.getMessage(), e);
            }

            file = new ZipEntryFile(this, entry);
            fileMap.put(localPath, file);
        }
        return file;
    }

    @Override
    public boolean move(String localPathFrom, String localPathTo, CopyOption... options) {
        if (localPathFrom == null)
            throw new NullPointerException("localPathFrom");
        if (localPathTo == null)
            throw new NullPointerException("localPathTo");

        if (localPathFrom.equals(localPathTo))
            return true;

        throw new NotImplementedException();
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws NotLinkException, IOException {
        throw new NotImplementedException();
    }

    /** ⇱ Implementation Of {@link IRemovableMedia}. */
    /* _____________________________ */static section.iface __REMOVABLE__;

    @Override
    public boolean isMediaRemoved() {
        return false;
    }

    @Override
    public void removeMedia()
            throws IOException {
        try {
            zipUnarchiver.close();
        } finally {
            zipUnarchiver = null;
        }
    }

    synchronized void loadEntry(ZipEntry entry)
            throws IOException {
        String name = entry.getName();
        ZipEntryFile file = (ZipEntryFile) resolve(name);
        if (file == null) {
            file = new ZipEntryFile(this, name);

        }
    }

    synchronized void loadAllEntries()
            throws IOException {
        if (!allEntriesLoaded) {
            for (ZipEntry entry : zipUnarchiver.entries())
                loadEntry(entry);
            allEntriesLoaded = true;
        }
    }

}
