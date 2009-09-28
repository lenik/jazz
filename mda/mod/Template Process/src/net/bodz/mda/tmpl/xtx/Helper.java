package net.bodz.mda.tmpl.xtx;

import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.lang.TRunnable;
import net.bodz.bas.sys.Processes;
import net.bodz.bas.types.util.Arrays2;

public class Helper {

    static class InterpretedCompiler implements TRunnable<String[], Exception> {

        private boolean  waitFor;
        private String[] shellCommand;

        public InterpretedCompiler(boolean waitFor, String[] shellCommand) {
            if (shellCommand == null)
                throw new NullPointerException("shellCommand");
            this.waitFor = waitFor;
            this.shellCommand = shellCommand;
        }

        @Override
        public void run(String[] parameters) throws Exception, ControlExit {
            if (parameters == null)
                parameters = new String[0];
            String[] cmdarray;
            if (parameters == null)
                cmdarray = parameters;
            else
                cmdarray = Arrays2.concat(shellCommand, parameters);
            Process process = Processes.shellExec(cmdarray);
            if (waitFor) {
                int exitValue = process.waitFor();
                throw new ControlExit(exitValue);
            }
        }
    }

    /**
     * @return the returned TRunnable will throw {@link ControlExit} with-in its
     *         {@link #run(String[])} method.
     */
    public static InterpretedCompiler interpretedCompiler(boolean waitFor, String... shellCommand) {
        return new InterpretedCompiler(waitFor, shellCommand);
    }

}
