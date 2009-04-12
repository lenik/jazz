package net.bodz.dist.ins.builtins;

import net.bodz.bas.cli.CLIInteraction;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.rt.Interaction;
import net.bodz.bas.types.TextMap;
import net.bodz.dist.ins.IProject;
import net.bodz.dist.ins.ProgressChangeEvent;
import net.bodz.dist.ins._Session;

public class CLISession extends _Session {

    CLIInteraction iact;

    LogOut         out = LogOuts.stdout;
    LogOut         err = LogOuts.stderr;

    public CLISession(IProject project, TextMap<Object> env, int logLevel) {
        super(project, env, logLevel);
        iact = new CLIInteraction();
    }

    @Override
    public Interaction getInteraction() {
        return iact;
    }

    // private Object[] lastLogArgs;

    @Override
    protected void _log(int level, Object... args) {
        if (level <= WARN)
            err.P(args);
        else
            out.P(args);
        // lastLogArgs = args;
    }

    @Override
    public void progressChange(ProgressChangeEvent e) {
    }

}
