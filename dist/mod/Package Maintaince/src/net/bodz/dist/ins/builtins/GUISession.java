package net.bodz.dist.ins.builtins;

import net.bodz.bas.gui.Interaction;
import net.bodz.bas.types.TextMap;
import net.bodz.dist.ins.ProgressPage;
import net.bodz.dist.ins._Project;
import net.bodz.dist.ins._Session;
import net.bodz.swt.gui.SWTInteraction;

public class GUISession extends _Session {

    private Interaction  iact;

    private ProgressPage progressPage;

    public GUISession(_Project project, TextMap<Object> env,
            ProgressPage progressPage) {
        super(project, env, 0);
        this.iact = new SWTInteraction();
        this.progressPage = progressPage;
    }

    @Override
    public Interaction getInteraction() {
        return iact;
    }

    @Override
    protected void updateProgress() {
        float p = getProgress();
        // progressPage.getP
    }

}
