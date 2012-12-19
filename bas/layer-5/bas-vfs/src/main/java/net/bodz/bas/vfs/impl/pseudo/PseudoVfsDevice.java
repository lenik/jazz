package net.bodz.bas.vfs.impl.pseudo;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.NotImplementedException;
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
        registeredFiles = new TreeMap<>();
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
    public PseudoPath parse(String localPath)
            throws BadPathException {
        PseudoFile pseudoFile = resolve(localPath);
        return pseudoFile.getPath();
    }

    @Override
    public PseudoFile resolve(String localPath)
            throws BadPathException {
        PseudoFile pseudoFile = registeredFiles.get(localPath);
        if (pseudoFile == null)
            throw new BadPathException("Not registered pseudo file path: " + localPath);
        else
            return pseudoFile;
    }

    @Override
    public PseudoFile resolve(IPath _path)
            throws FileResolveException {
        return resolve(_path.getLocalPath());
    }

    /**
     * In File object, it's safely to change the path value.
     */
    @Override
    public synchronized boolean rename(String localPathFrom, String localPathTo) {
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
        throw new NotImplementedException();
    }

}
