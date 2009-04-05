package net.bodz.dist.ins.builtins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.bodz.bas.io.Files;
import net.bodz.dist.ins.InstallException;
import net.bodz.dist.ins._Component;
import net.bodz.dist.ins._Project;

public class FileCopy extends _Component {

    private _Project project;
    private String   localName;
    private String   destName;
    private boolean  ignorable;

    public FileCopy(_Project project, String local, String dest) {
        this(project, true, local, dest, false);
    }

    public FileCopy(_Project project, boolean defaultSelected, String local,
            String dest, boolean ignorable) {
        super(project, false, defaultSelected);
        this.localName = local;
        this.destName = dest;
    }

    public File getLocal() {
        File cwd = null;
        File local = new File(cwd, localName);
        return local;
    }

    public File getDest() {
        File outdir = null;
        File dest = new File(outdir, destName);
        return dest;
    }

    @Override
    public boolean hasData() {
        return true;
    }

    @Override
    public void dump(OutputStream out) throws InstallException {
        File file = getLocal();
        try {
            for (byte[] block : Files.readByBlock(Files.blockSize, file)) {
                out.write(block);
            }
        } catch (IOException e) {
            throw new InstallException(e);
        }
    }

    @Override
    public boolean install(InputStream dumped) throws InstallException {
        File dest = getDest();
        try {
            L.d.P("Extract ", dest);
            FileOutputStream out = new FileOutputStream(dest);
            for (byte[] block : Files.readByBlock(Files.blockSize, dumped)) {
                out.write(block);
            }
            out.close();
        } catch (IOException e) {
            if (ignorable)
                return false;
            throw new InstallException(e);
        }
        return true;
    }

    @Override
    public void uninstall() throws InstallException {
        File dest = getDest();
        L.d.P("Delete ", dest);
        if (!dest.delete())
            L.d.P("can't delete");
    }

}
