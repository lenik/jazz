package net.bodz.bas.eclipse.cli;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class BatchProcessCLI extends net.bodz.bas.cli.BatchProcessCLI implements
        IApplication {

    @Override
    public Object start(IApplicationContext context) throws Exception {
        String[] args = Util.getArguments(context);
        try {
            run(args);
        } catch (Exception e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (Throwable e) {
            throw new Exception(e.getMessage(), e);
        }
        return EXIT_OK;
    }

    @Override
    public void stop() {
    }

}
