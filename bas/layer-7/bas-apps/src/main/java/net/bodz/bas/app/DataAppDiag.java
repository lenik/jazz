package net.bodz.bas.app;

import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.program.skel.BasicCLI;

/**
 * Data app diagnoistic utility
 */
public class DataAppDiag
        extends BasicCLI {

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        DataHub.getPreferredHub().dump(Stdio.cout.indented());
    }

    public static void main(String[] args)
            throws Exception {
        new DataAppDiag().execute(args);
    }

}
