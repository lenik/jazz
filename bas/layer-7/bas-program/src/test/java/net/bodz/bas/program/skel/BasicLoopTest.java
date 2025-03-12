package net.bodz.bas.program.skel;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class BasicLoopTest
        extends BasicLoop {

    static final Logger logger = LoggerFactory.getLogger(BasicLoopTest.class);

    @Override
    protected void setUp()
            throws Exception {
    }

    @Override
    protected boolean iterateMain() {
        System.out.println("[[ iterate main ]]");
        return true;
    }

    public static void main(String[] args)
            throws Exception {
        new BasicLoopTest().execute(args);
    }

}
