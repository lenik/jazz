package net.bodz.bas.make;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.meta.decl.NotNull;

public class ExecRule<T extends FileTarget>
        extends BasicMakeRule<T> {

    String executable;
    List<Object> args = new ArrayList<>();

    public ExecRule(@NotNull IMakefile makefile, @NotNull T target) {
        super(makefile, target);
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(@NotNull List<Object> args) {
        this.args = args;
    }

    @Override
    public void doMake()
            throws MakeException {
        List<String> argv = new ArrayList<>();
        for (Object arg : args) {
            String s = arg == null ? null : arg.toString();
            if (s == null)
                s = "";
            argv.add(s);
        }
        String[] cmdarray = argv.toArray(new String[0]);
        try {
            Process process = Processes.shellExec(cmdarray);
            int exitStatus = process.waitFor();
            if (exitStatus != 0)
                ;
        } catch (IOException e) {
            throw new MakeException(e);
        } catch (InterruptedException e) {
            throw new MakeException(e);
        }
    }

}
