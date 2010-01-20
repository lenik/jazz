package net.bodz.bas.cli.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.bodz.bas.fs.legacy.Files;
import net.bodz.bas.io.term.ITerminal;
import net.bodz.bas.io.term.Terminals;

public class ProtectedShell {

    private boolean enabled;
    private ITerminal out;

    public ProtectedShell(boolean enabled, ITerminal out) {
        this.enabled = enabled;
        this.out = out;
    }

    public ProtectedShell(boolean enabled) {
        this(enabled, Terminals.stderr);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean delete(File f) {
        out.p("delete ", f); 
        if (enabled)
            return f.delete();
        return false;
    }

    public void deleteOnExit(File f) {
        out.p("delete -x ", f); 
        if (enabled)
            f.deleteOnExit();
    }

    public boolean renameTo(File f, File dst) {
        out.p("rename ", f, " -> ", dst);  
        if (enabled)
            return f.renameTo(dst);
        return false;
    }

    public boolean setLastModified(File f, long time) {
        out.p("touch ", f, ": ", new Date(time));  
        if (enabled)
            return f.setLastModified(time);
        return false;
    }

    public boolean mkdirs(File f) {
        out.p("mkdirs ", f); 
        if (enabled)
            return f.mkdirs();
        return false;
    }

    public boolean move(File f, File dst) throws IOException {
        out.p("move ", f, " -> ", dst);  
        if (enabled)
            return Files.move(f, dst);
        return false;
    }

    public boolean move(File f, File dst, boolean force) throws IOException {
        out.p("move -f ", f, " -> ", dst);  
        if (enabled)
            return Files.move(f, dst, force);
        return false;
    }

    public boolean copy(Object src, Object dst) throws IOException {
        out.p("copy ", src, " -> ", dst);  
        if (enabled)
            return Files.copy(src, dst);
        return false;
    }

    public boolean copy(Object src, Object dst, boolean append) throws IOException {
        out.p("copy/append ", src, " -> ", dst);  
        if (enabled)
            return Files.copy(src, dst, append);
        return false;
    }

}
