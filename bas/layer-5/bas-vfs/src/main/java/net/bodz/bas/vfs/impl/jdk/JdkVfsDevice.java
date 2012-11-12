package net.bodz.bas.vfs.impl.jdk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.vfs.AbstractVfsDevice;
import net.bodz.bas.vfs.path.PathFormat;

public class JdkVfsDevice
        extends AbstractVfsDevice {

    public JdkVfsDevice() {
        super(JdkVfsDriver.getInstance());
    }

    @Override
    public List<? extends JdkFile> getRootFiles() {
        File[] roots = File.listRoots();
        List<JdkFile> rootFiles = new ArrayList<JdkFile>(roots.length);
        for (int i = 0; i < roots.length; i++) {
            JdkFile rootFile = new JdkFile(roots[i]);
            rootFiles.add(rootFile);
        }
        return rootFiles;
    }

    @Override
    public JdkPath parse(String localPath) {
        return new JdkPath(this, localPath);
    }

    @Override
    public JdkFile resolve(String localPath) {
        return new JdkFile(localPath);
    }

    @Override
    public String format(String localPath, PathFormat pathFormat) {
        return localPath;
    }

    @Override
    public boolean rename(String localPathFrom, String localPathTo) {
        File fileFrom = new File(localPathFrom);
        File fileTo = new File(localPathTo);
        return fileFrom.renameTo(fileTo);
    }

    private static final JdkVfsDevice instance = new JdkVfsDevice();

    public static JdkVfsDevice getInstance() {
        return instance;
    }

}
