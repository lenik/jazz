package net.bodz.dist.ins.builtins;

import net.bodz.bas.rt.Interaction;
import net.bodz.dist.ins.ProgressChangeEvent;
import net.bodz.dist.ins.ProgressPage;
import net.bodz.dist.ins._Project;
import net.bodz.dist.ins._Session;
import net.bodz.swt.gui.SWTInteraction;

public class GUISession extends _Session {

    private Interaction  iact;

    private ProgressPage progressPage;

    public GUISession(_Project project, ProgressPage progressPage) {
        super(project);
        this.iact = new SWTInteraction();
        this.progressPage = progressPage;
    }

    @Override
    public Interaction getInteraction() {
        return iact;
    }

    // @Override
    // protected void _log(int level, Object... args) {
    //        String s = Strings.join("", args); //$NON-NLS-1$
    // switch (level) {
    // case INFO:
    // case DETAIL:
    // progressPage.setText(s);
    // case FATAL:
    // case ERROR:
    // case WARN:
    // case DEBUG:
    // progressPage.log(level, s);
    // }
    // }

    @Override
    public void progressChange(ProgressChangeEvent e) {
        progressPage.setProgress(e.p);
    }

}
