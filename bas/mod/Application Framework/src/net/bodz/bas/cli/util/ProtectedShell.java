package net.bodz.bas.cli.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.bodz.bas.io.Files;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;

public class ProtectedShell {

    private boolean enabled;
    private ALog    L;

    public ProtectedShell(boolean enabled, ALog log) {
        this.enabled = enabled;
        this.L = log;
    }

    public ProtectedShell(boolean enabled) {
        this(enabled, ALogs.stderr);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean delete(File f) {
        L.d.P("delete ", f);
        if (enabled)
            return f.delete();
        return false;
    }

    public void deleteOnExit(File f) {
        L.d.P("delete -x ", f);
        if (enabled)
            f.deleteOnExit();
    }

    public boolean renameTo(File f, File dst) {
        L.d.P("rename ", f, " -> ", dst);
        if (enabled)
            return f.renameTo(dst);
        return false;
    }

    public boolean setLastModified(File f, long time) {
        L.d.P("touch ", f, ": ", new Date(time));
        if (enabled)
            return f.setLastModified(time);
        return false;
    }

    public boolean mkdirs(File f) {
        L.d.P("mkdirs ", f);
        if (enabled)
            return f.mkdirs();
        return false;
    }

    public boolean move(File f, File dst) throws IOException {
        L.d.P("move ", f, " -> ", dst);
        if (enabled)
            return Files.move(f, dst);
        return false;
    }

    public boolean move(File f, File dst, boolean force) throws IOException {
        L.d.P("move -f ", f, " -> ", dst);
        if (enabled)
            return Files.move(f, dst, force);
        return false;
    }

    public boolean copy(Object src, Object dst) throws IOException {
        L.d.P("copy ", src, " -> ", dst);
        if (enabled)
            return Files.copy(src, dst);
        return false;
    }

    public boolean copy(Object src, Object dst, boolean append)
            throws IOException {
        L.d.P("copy/append ", src, " -> ", dst);
        if (enabled)
            return Files.copy(src, dst, append);
        return false;
    }

}
