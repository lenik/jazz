package net.bodz.bas.vfs.impl.pseudo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.PathFormat;

public class PseudoVfsDevice
        extends AbstractVfsDevice {

    List<PseudoFile> rootFiles = new ArrayList<PseudoFile>();
    Map<String, PseudoFile> registeredFiles;

    public PseudoVfsDevice(PseudoVfsDriver driver) {
        super(driver);
        registeredFiles = new TreeMap<>();
    }

    @Override
    public List<? extends PseudoFile> getRootFiles() {
        return Collections.unmodifiableList(rootFiles);
    }

    public void setRootFiles(List<PseudoFile> rootFiles) {
        if (rootFiles == null)
            throw new NullPointerException("rootFiles");
        this.rootFiles = rootFiles;
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
    public String format(String localPath, PathFormat pathFormat) {
        return localPath;
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

}
