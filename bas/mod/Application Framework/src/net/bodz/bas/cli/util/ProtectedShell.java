package net.bodz.bas.cli.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.bodz.bas.io.Files;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.nls.AppNLS;

public class ProtectedShell {

    private boolean enabled;
    private LogOut  out;

    public ProtectedShell(boolean enabled, LogOut out) {
        this.enabled = enabled;
        this.out = out;
    }

    public ProtectedShell(boolean enabled) {
        this(enabled, LogOuts.stderr);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean delete(File f) {
        out.P(AppNLS.getString("ProtectedShell.delete"), f); //$NON-NLS-1$
        if (enabled)
            return f.delete();
        return false;
    }

    public void deleteOnExit(File f) {
        out.P(AppNLS.getString("ProtectedShell.deleteExit"), f); //$NON-NLS-1$
        if (enabled)
            f.deleteOnExit();
    }

    public boolean renameTo(File f, File dst) {
        out.P(AppNLS.getString("ProtectedShell.rename"), f, " -> ", dst); //$NON-NLS-1$ //$NON-NLS-2$
        if (enabled)
            return f.renameTo(dst);
        return false;
    }

    public boolean setLastModified(File f, long time) {
        out
                .P(
                        AppNLS.getString("ProtectedShell.touch"), f, ": ", new Date(time)); //$NON-NLS-1$ //$NON-NLS-2$
        if (enabled)
            return f.setLastModified(time);
        return false;
    }

    public boolean mkdirs(File f) {
        out.P(AppNLS.getString("ProtectedShell.mkdirs"), f); //$NON-NLS-1$
        if (enabled)
            return f.mkdirs();
        return false;
    }

    public boolean move(File f, File dst) throws IOException {
        out.P(AppNLS.getString("ProtectedShell.move"), f, " -> ", dst); //$NON-NLS-1$ //$NON-NLS-2$
        if (enabled)
            return Files.move(f, dst);
        return false;
    }

    public boolean move(File f, File dst, boolean force) throws IOException {
        out.P(AppNLS.getString("ProtectedShell.move_f"), f, " -> ", dst); //$NON-NLS-1$ //$NON-NLS-2$
        if (enabled)
            return Files.move(f, dst, force);
        return false;
    }

    public boolean copy(Object src, Object dst) throws IOException {
        out.P(AppNLS.getString("ProtectedShell.copy"), src, " -> ", dst); //$NON-NLS-1$ //$NON-NLS-2$
        if (enabled)
            return Files.copy(src, dst);
        return false;
    }

    public boolean copy(Object src, Object dst, boolean append)
            throws IOException {
        out.P(AppNLS.getString("ProtectedShell.copyAppend"), src, " -> ", dst); //$NON-NLS-1$ //$NON-NLS-2$
        if (enabled)
            return Files.copy(src, dst, append);
        return false;
    }

}
