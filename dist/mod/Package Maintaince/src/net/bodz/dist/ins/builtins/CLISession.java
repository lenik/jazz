package net.bodz.dist.ins.builtins;

import net.bodz.bas.cli.CLIInteraction;
import net.bodz.bas.rt.Interaction;
import net.bodz.dist.ins.IProject;
import net.bodz.dist.ins.ProgressChangeEvent;
import net.bodz.dist.ins._Session;

public class CLISession extends _Session {

    CLIInteraction iact;

    public CLISession(IProject project) {
        super(project);
        iact = new CLIInteraction();
        // logger ...?
    }

    @Override
    public Interaction getInteraction() {
        return iact;
    }

    // private Object[] lastLogArgs;
    // class LTerm extends _Terminal {
    //
    // }

    // @Override
    // protected void _log(int level, Object... args) {
    // int depth = getStack().size();
    // String indent = Strings.repeat(2 * depth, ' ');
    // if (level <= WARN)
    // err.p(indent, args);
    // else
    // out.p(indent, args);
    // // lastLogArgs = args;
    // }

    @Override
    public void progressChange(ProgressChangeEvent e) {
    }

}
