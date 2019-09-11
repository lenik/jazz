package net.bodz.bas.vfs.impl.pseudo;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.NotLinkException;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class PseudoVfsDevice
        extends AbstractVfsDevice {

    PseudoFile rootFile = null;
    Map<String, PseudoFile> registeredFiles;

    public PseudoVfsDevice(PseudoVfsDriver driver, String scopeName) {
        super(driver, driver.protocol, scopeName);
        registeredFiles = new TreeMap<String, PseudoFile>();
    }

    public String getScopeName() {
        return getDeviceSpec();
    }

    @Override
    public IFile getRootFile() {
        return rootFile;
    }

    public void setRootFile(PseudoFile rootFile) {
        this.rootFile = rootFile;
    }

    public void addPesudoFile(String localPath, PseudoFile pseudoFile) {
        if (localPath == null)
            throw new NullPointerException("localPath");
        if (pseudoFile == null)
            throw new NullPointerException("pseudoFile");
        registeredFiles.put(localPath, pseudoFile);
    }

    public void removePseudoFile(String localPath) {
        registeredFiles.remove(localPath);
    }

    @Override
    public PseudoPath parseLocalPath(String localPath)
            throws BadPathException {
        PseudoFile pseudoFile = _resolveNoRec(localPath);
        return pseudoFile.getPath();
    }

    @Override
    public PseudoFile _resolveNoRec(String localPath)
            throws BadPathException {
        PseudoFile pseudoFile = registeredFiles.get(localPath);
        if (pseudoFile == null)
            throw new BadPathException("Not registered pseudo file path: " + localPath);
        else
            return pseudoFile;
    }

    @Override
    public PseudoFile _resolveNoRec(IPath _path)
            throws FileResolveException {
        return _resolveNoRec(_path.getLocalPath());
    }

    /**
     * In File object, it's safely to change the path value.
     */
    @Override
    public synchronized boolean move(String localPathFrom, String localPathTo, CopyOption... options) {
        if (localPathFrom.equals(localPathTo))
            return false;

        PseudoFile fileFrom = registeredFiles.get(localPathFrom);
        PseudoFile fileTo = registeredFiles.get(localPathTo);

        // The file to be renamed is detached, nothing to do here.
        if (fileFrom == null)
            return true;

        // The dest file is already existed, don't rename.
        if (fileTo != null && fileTo != fileFrom)
            // OPTIM logException...
            return false;

        registeredFiles.remove(localPathFrom);
        fileFrom.setName(localPathTo);
        return true;
    }

    @Override
    public boolean createLink(String localPath, String target, boolean symbolic)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String readSymbolicLink(String localPath)
            throws NotLinkException, IOException {
        // PseudoFile file = resolve(localPath).inode;
        throw new UnsupportedOperationException();
    }

}
